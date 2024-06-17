package com.jrest.mvc.model;

import lombok.*;

/**
 * Класс представляет собой HTTP ответ.
 * <p>
 * Этот класс используется для создания и управления HTTP ответами, которые включают
 * в себя код ответа, протокол, заголовки и содержимое.
 * </p>
 */
@Setter
@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpResponse {

    /**
     * Создает новый HTTP ответ с указанным кодом ответа.
     *
     * @param responseCode код ответа
     * @return новый {@link HttpResponse} с указанным кодом ответа
     */
    public static HttpResponse of(ResponseCode responseCode) {
        return HttpResponse.builder()
                .code(responseCode)
                .build();
    }

    /**
     * Создает новый HTTP ответ с указанным кодом ответа и содержимым.
     *
     * @param responseCode код ответа
     * @param content содержимое ответа
     * @return новый {@link HttpResponse} с указанным кодом ответа и содержимым
     */
    public static HttpResponse of(ResponseCode responseCode, Content content) {
        return HttpResponse.builder()
                .code(responseCode)
                .content(content)
                .build();
    }

    /**
     * Создает новый HTTP ответ с кодом 200 OK.
     *
     * @return новый {@link HttpResponse} с кодом 200 OK
     */
    public static HttpResponse ok() {
        return of(ResponseCode.OK);
    }

    /**
     * Создает новый HTTP ответ с кодом 200 OK и указанным содержимым.
     *
     * @param content содержимое ответа
     * @return новый {@link HttpResponse} с кодом 200 OK и указанным содержимым
     */
    public static HttpResponse ok(Content content) {
        return of(ResponseCode.OK, content);
    }

    /**
     * Создает новый HTTP ответ с кодом 204 No Content.
     *
     * @return новый {@link HttpResponse} с кодом 204 No Content
     */
    public static HttpResponse noContent() {
        return of(ResponseCode.NO_CONTENT);
    }

    /**
     * Создает новый HTTP ответ с кодом 204 No Content и указанным содержимым.
     *
     * @param content содержимое ответа
     * @return новый {@link HttpResponse} с кодом 204 No Content и указанным содержимым
     */
    public static HttpResponse noContent(Content content) {
        return of(ResponseCode.NO_CONTENT, content);
    }

    /**
     * Создает новый HTTP ответ с кодом 404 Not Found.
     *
     * @return новый {@link HttpResponse} с кодом 404 Not Found
     */
    public static HttpResponse notFound() {
        return of(ResponseCode.NOT_FOUND);
    }

    /**
     * Создает новый HTTP ответ с кодом 404 Not Found и указанным содержимым.
     *
     * @param content содержимое ответа
     * @return новый {@link HttpResponse} с кодом 404 Not Found и указанным содержимым
     */
    public static HttpResponse notFound(Content content) {
        return of(ResponseCode.NOT_FOUND, content);
    }

    /**
     * Создает новый HTTP ответ с кодом 400 Bad Request.
     *
     * @return новый {@link HttpResponse} с кодом 400 Bad Request
     */
    public static HttpResponse badRequest() {
        return of(ResponseCode.BAD_REQUEST);
    }

    /**
     * Создает новый HTTP ответ с кодом 400 Bad Request и указанным содержимым.
     *
     * @param content содержимое ответа
     * @return новый {@link HttpResponse} с кодом 400 Bad Request и указанным содержимым
     */
    public static HttpResponse badRequest(Content content) {
        return of(ResponseCode.BAD_REQUEST, content);
    }

    /**
     * Создает новый HTTP ответ с кодом 500 Internal Server Error.
     *
     * @return новый {@link HttpResponse} с кодом 500 Internal Server Error
     */
    public static HttpResponse internalError() {
        return of(ResponseCode.INTERNAL_ERROR);
    }

    /**
     * Создает новый HTTP ответ с кодом 500 Internal Server Error и указанным содержимым.
     *
     * @param content содержимое ответа
     * @return новый {@link HttpResponse} с кодом 500 Internal Server Error и указанным содержимым
     */
    public static HttpResponse internalError(Content content) {
        return of(ResponseCode.INTERNAL_ERROR, content);
    }

    private HttpProtocol protocol;

    private ResponseCode code;

    private Headers headers;
    private Content content;
}
