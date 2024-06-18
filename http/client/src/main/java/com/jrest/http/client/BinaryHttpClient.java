package com.jrest.http.client;

import com.jrest.mvc.model.Attributes;
import com.jrest.mvc.model.HttpResponse;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Реализация HTTP-клиента, использующего
 * бинарные конфигурации для запросов.
 */
public interface BinaryHttpClient extends HttpClient {

    /**
     * Выполняет бинарный HTTP-запрос по имени.
     *
     * @param name имя конфигурации запроса.
     * @return опциональный HttpResponse.
     */
    Optional<HttpResponse> executeBinary(String name);

    /**
     * Выполняет бинарный HTTP-запрос по имени с дополнительными атрибутами.
     *
     * @param name  имя конфигурации запроса.
     * @param input дополнительные атрибуты для включения в запрос.
     * @return опциональный HttpResponse.
     */
    Optional<HttpResponse> executeBinary(String name, Attributes input);

    /**
     * Асинхронно выполняет бинарный HTTP-запрос по имени.
     *
     * @param name имя конфигурации запроса.
     * @return CompletableFuture с HttpResponse.
     */
    CompletableFuture<HttpResponse> executeBinaryAsync(String name);

    /**
     * Асинхронно выполняет бинарный HTTP-запрос по имени с дополнительными атрибутами.
     *
     * @param name  имя конфигурации запроса.
     * @param input дополнительные атрибуты для включения в запрос.
     * @return CompletableFuture с HttpResponse.
     */
    CompletableFuture<HttpResponse> executeBinaryAsync(String name, Attributes input);
}
