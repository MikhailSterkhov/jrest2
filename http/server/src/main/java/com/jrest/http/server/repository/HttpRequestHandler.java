package com.jrest.http.server.repository;

import com.jrest.http.api.HttpListener;
import com.jrest.mvc.model.HttpMethod;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс, представляющий обработчик репозитория HTTP-запросов.
 * Содержит информацию о пути URI, методе HTTP, вызове обработчика и флаге асинхронности.
 */
@Getter
@Builder
@ToString
public class HttpRequestHandler {

    private final String uri;
    private final HttpMethod httpMethod;
    private final HttpListener invocation;
    private final boolean notAuthorized;
    private final boolean isAsynchronous;

    /**
     * Проверяет, может ли текущий обработчик обработать данный HTTP-запрос.
     *
     * @param httpRequest HTTP-запрос
     * @return {@code true}, если обработчик может обработать запрос, иначе {@code false}
     */
    public boolean canProcess(HttpRequest httpRequest) {
        return (httpMethod == HttpMethod.ALL || httpRequest.getMethod().equals(getHttpMethod()));
    }
}
