package com.jrest.http.server.repository;

import com.jrest.http.server.resource.HttpServerResourceException;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.persistence.HttpAsync;
import com.jrest.mvc.persistence.HttpServer;
import com.jrest.mvc.persistence.util.HttpMvcMappersUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServerRepositoryValidator {

    public static HttpServerRepositoryValidator fromRepository(Object object) {
        return new HttpServerRepositoryValidator(object, object.getClass());
    }

    private final Object repository;
    private final Class<?> repositoryClass;

    public boolean isHttpServer() {
        return repositoryClass.isAnnotationPresent(HttpServer.class);
    }

    public List<HttpRepositoryHandler> findRepositoryHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsRequestMapping)
                .map(this::toHandler)
                .collect(Collectors.toList());
    }

    private HttpRepositoryHandler toHandler(Method method) {
        return HttpRepositoryHandler.builder()
                .uri(HttpMvcMappersUtil.findUri(method))
                .httpMethod(HttpMvcMappersUtil.toHttpMethod(method))
                .isAsynchronous(method.isAnnotationPresent(HttpAsync.class))
                .invocation(request -> invoke(request, method))
                .build();
    }

    private HttpResponse invoke(HttpRequest request, Method method) {
        if (!method.getReturnType().equals(HttpResponse.class)) {
            throw new HttpServerResourceException("Method `" + method + "` must be return HttpResponse type");
        }
        try {
            Object[] args = method.getParameterCount() > 1 ? new Object[]{request} : new Object[0];
            return (HttpResponse) method.invoke(repository, args);
        }
        catch (Exception exception) {
            throw new HttpServerRepositoryException("Http-server repository invocation from " + method, exception);
        }
    }
}
