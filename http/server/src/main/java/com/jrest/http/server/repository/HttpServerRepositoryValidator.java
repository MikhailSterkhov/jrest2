package com.jrest.http.server.repository;

import com.jrest.http.api.HttpListener;
import com.jrest.http.server.resource.HttpServerResourceException;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.persistence.HttpMvcMappersUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Валидатор для проверки и обработки репозиториев HTTP-сервера.
 * Содержит методы для поиска и обработки аннотированных методов в репозитории.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServerRepositoryValidator {

    private final Object repository;
    private final Class<?> repositoryClass;

    /**
     * Создает новый экземпляр валидатора для указанного репозитория.
     *
     * @param object репозиторий
     * @return экземпляр HttpServerRepositoryValidator
     */
    public static HttpServerRepositoryValidator fromRepository(Object object) {
        return new HttpServerRepositoryValidator(object, object.getClass());
    }

    /**
     * Проверяет, аннотирован ли класс репозитория как HttpServer.
     *
     * @return {@code true}, если класс аннотирован, иначе {@code false}
     */
    public boolean isHttpServer() {
        return HttpMvcMappersUtil.isAnnotatedAsHttpServer(repositoryClass);
    }

    /**
     * Находит обработчики запросов в репозитории.
     *
     * @return список обработчиков запросов
     */
    public List<HttpRepositoryHandler> findProcessingHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsRequestMapping)
                .map(this::toHandler)
                .collect(Collectors.toList());
    }

    /**
     * Находит обработчики, выполняемые перед основными обработчиками запросов.
     *
     * @return список обработчиков перед выполнением
     */
    public List<HttpRepositoryHandler> findBeforeHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsBeforeExecution)
                .map(this::toHandler)
                .collect(Collectors.toList());
    }

    /**
     * Преобразует метод репозитория в обработчик запросов.
     *
     * @param method метод репозитория
     * @return обработчик запросов
     */
    private HttpRepositoryHandler toHandler(Method method) {
        method.setAccessible(true);
        return HttpRepositoryHandler.builder()
                .uri(HttpMvcMappersUtil.findUri(method))
                .httpMethod(HttpMvcMappersUtil.toHttpMethod(method))
                .isAsynchronous(HttpMvcMappersUtil.isAnnotatedAsAsync(method))
                .invocation(request -> invoke(request, method))
                .build();
    }

    /**
     * Выполняет вызов метода репозитория с заданным HTTP-запросом.
     *
     * @param request HTTP-запрос
     * @param method метод репозитория
     * @return HTTP-ответ
     */
    private HttpResponse invoke(HttpRequest request, Method method) {
        boolean isVoid = method.getReturnType().equals(void.class);
        if (!method.getReturnType().equals(HttpResponse.class) && !isVoid) {
            throw new HttpServerResourceException("Method `" + method + "` must return HttpResponse type");
        }
        try {
            Object[] args = method.getParameterCount() > 0 ? new Object[]{request} : new Object[0];
            if (isVoid) {
                method.invoke(repository, args);
                return HttpListener.SKIP_ACTION;
            }
            return (HttpResponse) method.invoke(repository, args);
        } catch (Exception exception) {
            throw new HttpServerRepositoryException("Http-server repository invocation from " + method, exception);
        }
    }
}
