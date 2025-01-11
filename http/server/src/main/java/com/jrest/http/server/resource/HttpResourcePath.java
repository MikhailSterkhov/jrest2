package com.jrest.http.server.resource;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.net.URI;

/**
 * Класс для представления пути ресурса HTTP.
 * Содержит методы для создания и сравнения путей ресурсов.
 */
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpResourcePath {

    /**
     * Полный путь ресурса.
     */
    private final String full;

    /**
     * Создает экземпляр {@code HttpResourcePath} из строки URI.
     *
     * @param uri строка URI
     * @return экземпляр {@code HttpResourcePath}
     */
    public static HttpResourcePath fromUri(String uri) {
        return new HttpResourcePath(uri);
    }

    /**
     * Проверяет, соответствует ли запрашиваемый URI этому ресурсу.
     *
     * @param requestUri запрашиваемый URI
     * @return {@code true}, если запрашиваемый URI соответствует этому ресурсу, иначе {@code false}
     */
    boolean isExpected(String requestUri) {
        if (full.equals("*")) {
            return true;
        }
        if (requestUri.contains("?")) {
            requestUri = requestUri.substring(0, requestUri.indexOf("?"));
        }
        return requestUri.equalsIgnoreCase(full);
    }

    /**
     * Преобразует путь ресурса в объект {@code URI}.
     *
     * @return объект {@code URI}, представляющий путь ресурса
     */
    public URI toURI() {
        return URI.create(full);
    }
}
