package com.jrest.http.client;

import com.jrest.mvc.model.*;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Интерфейс HttpClient предоставляет
 * методы для выполнения HTTP-запросов
 * от лица клиентской части соединения.
 */
public interface HttpClient extends Serializable {

    /**
     * Создает объект запроса.
     *
     * @param httpRequest объект запроса HttpRequest.
     * @return объект запроса ClientHttpRequest.
     */
    ClientHttpRequest create(HttpRequest httpRequest);

    /**
     * Выполняет HTTP-запрос синхронно.
     *
     * @param httpRequest объект запроса HttpRequest.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> execute(HttpRequest httpRequest);

    /**
     * Выполняет HTTP-запрос асинхронно.
     *
     * @param httpRequest объект запроса HttpRequest.
     * @return CompletableFuture с объектом HttpResponse.
     */
    CompletableFuture<HttpResponse> executeAsync(HttpRequest httpRequest);

    //

    /**
     * Выполняет HTTP GET запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeGet(String url);

    /**
     * Выполняет HTTP GET запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeGet(String url, Content content);

    /**
     * Выполняет HTTP GET запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeGet(String url, Headers headers);

    /**
     * Выполняет HTTP GET запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeGet(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP GET запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeGet(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP GET запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeGet(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP GET запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeGet(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP POST запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeDelete(String url);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeDelete(String url, Content content);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeDelete(String url, Headers headers);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeDelete(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeDelete(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeDelete(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeDelete(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP POST запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePost(String url);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePost(String url, Content content);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePost(String url, Headers headers);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePost(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePost(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePost(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP POST запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePost(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP PUT запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePut(String url);

    /**
     * Выполняет HTTP PUT запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePut(String url, Content content);

    /**
     * Выполняет HTTP PUT запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePut(String url, Headers headers);

    /**
     * Выполняет HTTP PUT запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePut(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP PUT запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePut(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP PUT запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePut(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP PUT запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePut(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP TRACE запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeTrace(String url);

    /**
     * Выполняет HTTP TRACE запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeTrace(String url, Content content);

    /**
     * Выполняет HTTP TRACE запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeTrace(String url, Headers headers);

    /**
     * Выполняет HTTP TRACE запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeTrace(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP TRACE запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeTrace(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP TRACE запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeTrace(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP TRACE запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeTrace(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP HEAD запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeHead(String url);

    /**
     * Выполняет HTTP HEAD запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeHead(String url, Content content);

    /**
     * Выполняет HTTP HEAD запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeHead(String url, Headers headers);

    /**
     * Выполняет HTTP HEAD запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeHead(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP HEAD запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeHead(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP HEAD запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeHead(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP HEAD запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeHead(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP OPTIONS запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeOptions(String url);

    /**
     * Выполняет HTTP OPTIONS запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeOptions(String url, Content content);

    /**
     * Выполняет HTTP OPTIONS запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeOptions(String url, Headers headers);

    /**
     * Выполняет HTTP OPTIONS запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeOptions(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP OPTIONS запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeOptions(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP OPTIONS запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeOptions(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP OPTIONS запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeOptions(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP CONNECT запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeConnect(String url);

    /**
     * Выполняет HTTP CONNECT запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeConnect(String url, Content content);

    /**
     * Выполняет HTTP CONNECT запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeConnect(String url, Headers headers);

    /**
     * Выполняет HTTP CONNECT запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeConnect(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP CONNECT запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeConnect(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP CONNECT запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeConnect(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP CONNECT запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executeConnect(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP PATCH запрос по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePatch(String url);

    /**
     * Выполняет HTTP PATCH запрос по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePatch(String url, Content content);

    /**
     * Выполняет HTTP PATCH запрос по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePatch(String url, Headers headers);

    /**
     * Выполняет HTTP PATCH запрос по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePatch(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP PATCH запрос по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePatch(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP PATCH запрос по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePatch(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP PATCH запрос по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    Optional<HttpResponse> executePatch(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP GET запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncGet(String url);

    /**
     * Выполняет HTTP GET запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncGet(String url, Content content);

    /**
     * Выполняет HTTP GET запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncGet(String url, Headers headers);

    /**
     * Выполняет HTTP GET запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncGet(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP GET запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncGet(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP GET запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncGet(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP GET запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncGet(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP DELETE запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncDelete(String url);

    /**
     * Выполняет HTTP DELETE запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncDelete(String url, Content content);

    /**
     * Выполняет HTTP DELETE запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncDelete(String url, Headers headers);

    /**
     * Выполняет HTTP DELETE запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncDelete(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP DELETE запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncDelete(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP DELETE запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncDelete(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP DELETE запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncDelete(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP POST запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncPost(String url);

    /**
     * Выполняет HTTP POST запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPost(String url, Content content);

    /**
     * Выполняет HTTP POST запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPost(String url, Headers headers);

    /**
     * Выполняет HTTP POST запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPost(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP POST запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPost(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP POST запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPost(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP POST запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPost(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP PUT запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncPut(String url);

    /**
     * Выполняет HTTP PUT запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPut(String url, Content content);

    /**
     * Выполняет HTTP PUT запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPut(String url, Headers headers);

    /**
     * Выполняет HTTP PUT запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPut(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP PUT запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPut(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP PUT запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPut(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP PUT запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPut(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP TRACE запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncTrace(String url);

    /**
     * Выполняет HTTP TRACE запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncTrace(String url, Content content);

    /**
     * Выполняет HTTP TRACE запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncTrace(String url, Headers headers);

    /**
     * Выполняет HTTP TRACE запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncTrace(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP TRACE запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncTrace(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP TRACE запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncTrace(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP TRACE запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncTrace(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP HEAD запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncHead(String url);

    /**
     * Выполняет HTTP HEAD запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncHead(String url, Content content);

    /**
     * Выполняет HTTP HEAD запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncHead(String url, Headers headers);

    /**
     * Выполняет HTTP HEAD запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncHead(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP HEAD запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncHead(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP HEAD запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncHead(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP HEAD запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncHead(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP OPTIONS запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncOptions(String url);

    /**
     * Выполняет HTTP OPTIONS запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncOptions(String url, Content content);

    /**
     * Выполняет HTTP OPTIONS запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncOptions(String url, Headers headers);

    /**
     * Выполняет HTTP OPTIONS запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncOptions(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP OPTIONS запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncOptions(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP OPTIONS запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncOptions(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP OPTIONS запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncOptions(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP CONNECT запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncConnect(String url);

    /**
     * Выполняет HTTP CONNECT запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncConnect(String url, Content content);

    /**
     * Выполняет HTTP CONNECT запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncConnect(String url, Headers headers);

    /**
     * Выполняет HTTP CONNECT запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncConnect(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP CONNECT запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncConnect(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP CONNECT запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncConnect(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP CONNECT запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncConnect(String url, Attributes attributes, Content content, Headers headers);

    //

    /**
     * Выполняет HTTP PATCH запрос асинхронно по-указанному URL.
     *
     * @param url URL для запроса.
     * @return объект HttpResponse, обернутый в Optional.
     */
    CompletableFuture<HttpResponse> executeAsyncPatch(String url);

    /**
     * Выполняет HTTP PATCH запрос асинхронно по-указанному URL с указанным контентом.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPatch(String url, Content content);

    /**
     * Выполняет HTTP PATCH запрос асинхронно по-указанному URL с указанными заголовками.
     *
     * @param url URL для запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPatch(String url, Headers headers);

    /**
     * Выполняет HTTP PATCH запрос асинхронно по-указанному URL с указанным контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPatch(String url, Content content, Headers headers);

    /**
     * Выполняет HTTP PATCH запрос асинхронно по-указанному URL с указанными атрибутами и контентом.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPatch(String url, Attributes attributes, Content content);

    /**
     * Выполняет HTTP PATCH запрос асинхронно по-указанному URL с указанными атрибутами и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPatch(String url, Attributes attributes, Headers headers);

    /**
     * Выполняет HTTP PATCH запрос асинхронно по-указанному URL с указанными атрибутами, контентом и заголовками.
     *
     * @param url URL для запроса.
     * @param attributes атрибуты запроса.
     * @param content контент запроса.
     * @param headers заголовки запроса.
     * @return объект HttpResponse, обернутый в CompletableFuture.
     */
    CompletableFuture<HttpResponse> executeAsyncPatch(String url, Attributes attributes, Content content, Headers headers);
}
