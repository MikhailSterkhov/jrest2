package com.jrest.mvc.model;

import com.jrest.mvc.model.util.UrlPathUtil;
import lombok.*;

/**
 * Класс представляет собой HTTP запрос.
 * <p>
 * Этот класс используется для создания и управления HTTP запросами, которые включают
 * в себя метод запроса, URL, атрибуты, содержимое и заголовки.
 * </p>
 */
@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpRequest {

    private HttpMethod method;

    private String url;

    private Attributes attributes;
    private Content content;
    private Headers headers;

    /**
     * Возвращает путь из URL запроса.
     * <p>
     * Метод извлекает путь из полного URL запроса, исключая протокол и домен.
     * Например, из URL "http://example.com/path/to/resource" метод вернет "/path/to/resource".
     * </p>
     *
     * @return строка, представляющая путь URL
     */
    public String getPath() {
        return UrlPathUtil.stripPath(url);
    }
}
