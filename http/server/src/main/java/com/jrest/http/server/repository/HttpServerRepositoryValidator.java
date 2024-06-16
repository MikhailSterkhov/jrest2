package com.jrest.http.server.repository;

import com.jrest.http.api.HttpListener;
import com.jrest.http.server.resource.HttpServerResourceException;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
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
        return HttpMvcMappersUtil.isAnnotatedAsHttpServer(repositoryClass);
    }

    public List<HttpRepositoryHandler> findProcessingHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsRequestMapping)
                .map(this::toHandler)
                .collect(Collectors.toList());
    }

    public List<HttpRepositoryHandler> findBeforeHandlers() {
        return Arrays.stream(repositoryClass.getMethods())
                .filter(HttpMvcMappersUtil::isAnnotatedAsBeforeExecution)
                .map(this::toHandler)
                .collect(Collectors.toList());
    }

    private HttpRepositoryHandler toHandler(Method method) {
        method.setAccessible(true);
        return HttpRepositoryHandler.builder()
                .uri(HttpMvcMappersUtil.findUri(method))
                .httpMethod(HttpMvcMappersUtil.toHttpMethod(method))
                .isAsynchronous(HttpMvcMappersUtil.isAnnotatedAsAsync(method))
                .invocation(request -> invoke(request, method))
                .build();
    }

    private HttpResponse invoke(HttpRequest request, Method method) {
        boolean isVoid = method.getReturnType().equals(void.class);
        if (!method.getReturnType().equals(HttpResponse.class) && !isVoid) {
            throw new HttpServerResourceException("Method `" + method + "` must be return HttpResponse type");
        }
        try {
            Object[] args = method.getParameterCount() > 0 ? new Object[]{request} : new Object[0];
            if (isVoid) {
                method.invoke(repository, args);
                return HttpListener.SKIP_ACTION;
            }
            return (HttpResponse) method.invoke(repository, args);
        }
        catch (Exception exception) {
            throw new HttpServerRepositoryException("Http-server repository invocation from " + method, exception);
        }
    }
}
