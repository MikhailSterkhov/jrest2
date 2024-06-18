package com.jrest.mvc.persistence;

import com.jrest.mvc.model.HttpMethod;
import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Утилитный класс для работы с аннотациями маппинга HTTP запросов.
 * <p>
 * Этот класс предоставляет методы для проверки аннотированных методов и классов, а также для
 * преобразования аннотаций в соответствующие HTTP методы и URI.
 * </p>
 */
@UtilityClass
public class HttpMvcMappersUtil {

    private static final List<Class<? extends Annotation>> MAPPERS_ANNOTATIONS =
            Arrays.asList(
                    HttpBeforeAll.class,
                    HttpRequestMapping.class,

                    // http methods wrappers.
                    HttpGet.class,
                    HttpDelete.class,
                    HttpPost.class,
                    HttpPut.class,
                    HttpConnect.class,
                    HttpPatch.class,
                    HttpTrace.class
            );

    private static Map<Class<? extends Annotation>, Function<Annotation, HttpMethod>> HTTP_METHODS_BY_MAPPERS;
    private static Map<Class<? extends Annotation>, Function<Annotation, String>> URI_GETTERS_BY_MAPPERS;

    /**
     * Проверяет, аннотирован ли метод как запрос HTTP.
     *
     * @param method метод для проверки
     * @return {@code true}, если метод аннотирован как запрос HTTP, иначе {@code false}
     */
    public boolean isAnnotatedAsRequestMapping(Method method) {
        initMapsLazy();
        return MAPPERS_ANNOTATIONS.stream().anyMatch(method::isAnnotationPresent);
    }

    /**
     * Проверяет, аннотирован ли метод как выполняемый предварительную авторизацию.
     *
     * @param method метод для проверки
     * @return {@code true}, если метод аннотирован как выполняемый предварительную авторизацию, иначе {@code false}
     */
    public boolean isAnnotatedAsAuthenticator(Method method) {
        initMapsLazy();
        return method.isAnnotationPresent(HttpAuthenticator.class);
    }

    public boolean isAnnotatedAsNotAuthorized(Method method) {
        initMapsLazy();
        return method.isAnnotationPresent(HttpNotAuthorized.class);
    }

    /**
     * Проверяет, аннотирован ли метод как выполняемый перед обработкой HTTP запроса.
     *
     * @param method метод для проверки
     * @return {@code true}, если метод аннотирован как выполняемый перед обработкой HTTP запроса, иначе {@code false}
     */
    public boolean isAnnotatedAsBeforeExecution(Method method) {
        initMapsLazy();
        return method.isAnnotationPresent(HttpBeforeAll.class);
    }

    /**
     * Проверяет, аннотирован ли метод как асинхронный запрос HTTP.
     *
     * @param method метод для проверки
     * @return {@code true}, если метод аннотирован как асинхронный запрос HTTP, иначе {@code false}
     */
    public boolean isAnnotatedAsAsync(Method method) {
        initMapsLazy();
        return method.isAnnotationPresent(HttpAsync.class);
    }

    /**
     * Проверяет, аннотирован ли класс как HTTP сервер.
     *
     * @param cls класс для проверки
     * @return {@code true}, если класс аннотирован как HTTP сервер, иначе {@code false}
     */
    public boolean isAnnotatedAsHttpServer(Class<?> cls) {
        initMapsLazy();
        return cls.isAnnotationPresent(HttpServer.class);
    }

    /**
     * Преобразует аннотацию метода в соответствующий HTTP метод.
     *
     * @param method метод для преобразования
     * @return соответствующий {@link HttpMethod} или {@code HttpMethod.UNKNOWN}, если аннотация не распознана
     * @throws HttpMvcException если метод аннотирован более чем одной аннотацией маппинга
     */
    public HttpMethod toHttpMethod(Method method) {
        initMapsLazy();

        List<Class<? extends Annotation>> annotationTypesList = MAPPERS_ANNOTATIONS.stream()
                .filter(method::isAnnotationPresent)
                .collect(Collectors.toList());

        if (annotationTypesList.isEmpty()) {
            return HttpMethod.UNKNOWN;
        }
        if (annotationTypesList.size() > 1) {
            throw new HttpMvcException("Method `" + method + "` uses more than one request-mapper annotation");
        }

        Class<? extends Annotation> annotationType = annotationTypesList.get(0);

        return Optional.ofNullable(HTTP_METHODS_BY_MAPPERS.get(annotationType))
                .map(func -> func.apply(method.getDeclaredAnnotation(annotationType)))
                .orElse(HttpMethod.UNKNOWN);
    }

    /**
     * Находит URI, связанный с аннотацией метода.
     *
     * @param method метод для поиска URI
     * @return URI, связанный с аннотацией метода
     * @throws HttpMvcException если метод не аннотирован аннотацией маппинга или аннотирован более чем одной аннотацией
     */
    public String findUri(Method method) {
        initMapsLazy();

        List<Class<? extends Annotation>> annotationTypesList = MAPPERS_ANNOTATIONS.stream()
                .filter(method::isAnnotationPresent)
                .collect(Collectors.toList());

        if (annotationTypesList.isEmpty()) {
            throw new HttpMvcException("Method `" + method + "` not used request-mappers annotations");
        }
        if (annotationTypesList.size() > 1) {
            throw new HttpMvcException("Method `" + method + "` uses more than one request-mapper annotation");
        }

        Class<? extends Annotation> annotationType = annotationTypesList.get(0);
        Function<Annotation, String> function = URI_GETTERS_BY_MAPPERS.get(annotationType);

        return function == null ? null : function.apply(method.getDeclaredAnnotation(annotationType));
    }

    /**
     * Ленивая инициализация карт соответствий аннотаций HTTP методам и URI.
     */
    private void initMapsLazy() {
        if (HTTP_METHODS_BY_MAPPERS == null || HTTP_METHODS_BY_MAPPERS.isEmpty()) {
            HTTP_METHODS_BY_MAPPERS = new HashMap<>();
            registerMethodGetter(HttpBeforeAll.class, (a) -> HttpMethod.fromName(a.method()));
            registerMethodGetter(HttpRequestMapping.class, (a) -> HttpMethod.fromName(a.method()));
            registerMethodGetter(HttpGet.class, (a) -> HttpMethod.GET);
            registerMethodGetter(HttpDelete.class, (a) -> HttpMethod.DELETE);
            registerMethodGetter(HttpPost.class, (a) -> HttpMethod.POST);
            registerMethodGetter(HttpPut.class, (a) -> HttpMethod.PUT);
            registerMethodGetter(HttpConnect.class, (a) -> HttpMethod.CONNECT);
            registerMethodGetter(HttpPatch.class, (a) -> HttpMethod.PATCH);
            registerMethodGetter(HttpTrace.class, (a) -> HttpMethod.TRACE);
        }

        if (URI_GETTERS_BY_MAPPERS == null || URI_GETTERS_BY_MAPPERS.isEmpty()) {
            URI_GETTERS_BY_MAPPERS = new HashMap<>();
            registerUriGetter(HttpBeforeAll.class, HttpBeforeAll::path);
            registerUriGetter(HttpRequestMapping.class, HttpRequestMapping::path);
            registerUriGetter(HttpGet.class, HttpGet::value);
            registerUriGetter(HttpDelete.class, HttpDelete::value);
            registerUriGetter(HttpPost.class, HttpPost::value);
            registerUriGetter(HttpPut.class, HttpPut::value);
            registerUriGetter(HttpConnect.class, HttpConnect::value);
            registerUriGetter(HttpPatch.class, HttpPatch::value);
            registerUriGetter(HttpTrace.class, HttpTrace::value);
        }
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> void registerMethodGetter(Class<T> cls, Function<T, HttpMethod> function) {
        HTTP_METHODS_BY_MAPPERS.put(cls, (Function<Annotation, HttpMethod>) function);
    }

    @SuppressWarnings("unchecked")
    private <T extends Annotation> void registerUriGetter(Class<T> cls, Function<T, String> function) {
        URI_GETTERS_BY_MAPPERS.put(cls, (Function<Annotation, String>) function);
    }
}
