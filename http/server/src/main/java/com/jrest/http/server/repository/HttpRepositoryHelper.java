package com.jrest.http.server.repository;

import com.jrest.http.api.HttpListener;
import com.jrest.http.server.HttpServerException;
import com.jrest.http.server.resource.HttpServerResourceException;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.model.authentication.ApprovalResult;
import com.jrest.mvc.model.authentication.UnapprovedRequest;
import com.jrest.mvc.persistence.HttpMvcMappersUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Помощник для проверки и обработки репозиториев HTTP-сервера.
 * Содержит методы для поиска и обработки аннотированных методов в репозитории.
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpRepositoryHelper {

    private final Object repository;
    private final Class<?> repositoryClass;

    /**
     * Создает новый экземпляр помощника для указанного репозитория.
     *
     * @param object репозиторий
     * @return экземпляр HttpRepositoryHelper
     */
    public static HttpRepositoryHelper fromRepository(Object object) {
        return new HttpRepositoryHelper(object, object.getClass());
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
     * Находит обработчики авторизации в репозитории.
     *
     * @return список обработчиков авторизации
     */
    public List<HttpAuthorizationHandler> findAuthorizationHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsAuthenticator)
                .map(this::toAuthorizationHandler)
                .collect(Collectors.toList());
    }

    /**
     * Находит обработчики запросов в репозитории.
     *
     * @return список обработчиков запросов
     */
    public List<HttpRequestHandler> findProcessingHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsRequestMapping)
                .map(this::toRequestHandler)
                .collect(Collectors.toList());
    }

    /**
     * Находит обработчики, выполняемые перед основными обработчиками запросов.
     *
     * @return список обработчиков перед выполнением
     */
    public List<HttpRequestHandler> findBeforeHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsBeforeExecution)
                .map(this::toRequestHandler)
                .collect(Collectors.toList());
    }

    /**
     * Преобразует метод репозитория в обработчик авторизации.
     *
     * @param method метод репозитория
     * @return обработчик авторизации
     */
    private HttpAuthorizationHandler toAuthorizationHandler(Method method) {
        method.setAccessible(true);
        return HttpAuthorizationHandler.builder()
                .isAsynchronous(HttpMvcMappersUtil.isAnnotatedAsAsync(method))
                .authenticator(request -> invokeAuthorization(request, method))
                .build();
    }

    /**
     * Преобразует метод репозитория в обработчик запроса.
     *
     * @param method метод репозитория
     * @return обработчик запросов
     */
    private HttpRequestHandler toRequestHandler(Method method) {
        method.setAccessible(true);
        return HttpRequestHandler.builder()
                .uri(HttpMvcMappersUtil.findUri(method))
                .method(HttpMvcMappersUtil.toHttpMethod(method))
                .notAuthorized(HttpMvcMappersUtil.isAnnotatedAsBeforeExecution(method) || HttpMvcMappersUtil.isAnnotatedAsNotAuthorized(method))
                .isAsynchronous(HttpMvcMappersUtil.isAnnotatedAsAsync(method))
                .invocation(request -> invokeRequest(request, method))
                .build();
    }

    /**
     * Выполняет вызов метода репозитория с заданным HTTP-запросом.
     *
     * @param request HTTP-запрос
     * @param method метод репозитория
     * @return HTTP-ответ
     */
    private HttpResponse invokeRequest(HttpRequest request, Method method) {
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
            throw new HttpServerException("Http-server repository failed invocation from " + method, exception);
        }
    }

    /**
     * Выполняет вызов метода репозитория с заданным неутвержденным запросом.
     *
     * @param request неутвержденный запрос
     * @param method метод репозитория
     * @return результат авторизации
     */
    private ApprovalResult invokeAuthorization(UnapprovedRequest request, Method method) {
        if (!method.getReturnType().equals(ApprovalResult.class)) {
            throw new HttpServerResourceException("Method `" + method + "` must return ApprovalResult type");
        }
        try {
            Object[] args = method.getParameterCount() > 0 ? new Object[]{request} : new Object[0];
            return (ApprovalResult) method.invoke(repository, args);
        } catch (Exception exception) {
            throw new HttpServerException("Http-server repository failed invocation from " + method, exception);
        }
    }
}
