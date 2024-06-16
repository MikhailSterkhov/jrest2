package com.jrest.http.client;

import com.jrest.mvc.model.Attributes;
import com.jrest.mvc.model.Content;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpMethod;
import com.jrest.mvc.model.HttpResponse;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс для HTTP запроса клиента.
 */
public interface ClientHttpRequest extends Serializable {

    /**
     * Возвращает атрибуты HTTP запроса.
     *
     * @return атрибуты HTTP запроса
     */
    Attributes attributes();

    /**
     * Возвращает заголовки HTTP запроса.
     *
     * @return заголовки HTTP запроса
     */
    Headers headers();

    /**
     * Возвращает HTTP метод, используемый в запросе.
     *
     * @return HTTP метод запроса
     */
    HttpMethod method();

    /**
     * Возвращает тело HTTP запроса.
     *
     * @return тело HTTP запроса
     */
    Content body();

    /**
     * Выполняет синхронный HTTP запрос и возвращает опциональный ответ.
     *
     * @return опциональный ответ HTTP
     */
    Optional<HttpResponse> execute();

    /**
     * Выполняет асинхронный HTTP запрос и возвращает CompletableFuture с ответом.
     *
     * @return CompletableFuture с ответом HTTP
     */
    CompletableFuture<HttpResponse> executeAsync();
}
