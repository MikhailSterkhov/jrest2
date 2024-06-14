package com.jrest.mvc.model;

import lombok.*;

import java.util.*;

/**
 * Класс Headers предоставляет методы для работы с HTTP-заголовками.
 * Заголовки хранятся в виде карты, где ключом является имя заголовка,
 * а значением - список значений заголовка.
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Headers {

    /**
     * Создает новый объект Headers с пустым набором заголовков.
     *
     * @return новый объект Headers.
     */
    public static Headers newHeaders() {
        return new Headers(new HashMap<>());
    }

    /**
     * Создает новый объект Headers из переданной карты заголовков.
     *
     * @param map карта, содержащая заголовки и их значения.
     * @return новый объект Headers.
     */
    public static Headers fromMap(Map<String, List<String>> map) {
        return new Headers(map);
    }

    private final Map<String, List<String>> headers;

    /**
     * Добавляет новое значение к заголовку. Если заголовок не существует, он будет создан.
     *
     * @param header имя заголовка.
     * @param value значение заголовка.
     * @return текущий объект Headers.
     */
    public Headers add(String header, Object value) {
        List<String> strings = headers.get(header);
        if (strings == null) {
            strings = new ArrayList<>();
        }
        strings.add(value.toString());
        headers.put(header, strings);
        return this;
    }

    /**
     * Устанавливает значение для заголовка. Если заголовок существует, его значение будет перезаписано.
     *
     * @param header имя заголовка.
     * @param value значение заголовка.
     * @return текущий объект Headers.
     */
    public Headers set(String header, Object value) {
        if (value == null) {
            return this;
        }
        headers.put(header, new ArrayList<>(Collections.singletonList(value.toString())));
        return this;
    }

    /**
     * Удаляет значение из заголовка. Если значение не существует, метод ничего не делает.
     *
     * @param header имя заголовка.
     * @param value значение заголовка.
     * @return текущий объект Headers.
     */
    public Headers remove(String header, Object value) {
        List<String> values = headers.get(header);
        if (values != null) {
            values.remove(value.toString());
        }
        return this;
    }

    /**
     * Удаляет заголовок и все его значения.
     *
     * @param header имя заголовка.
     * @return текущий объект Headers.
     */
    public Headers removeFull(String header) {
        headers.remove(header);
        return this;
    }

    /**
     * Очищает все заголовки.
     *
     * @return текущий объект Headers.
     */
    public Headers clear() {
        headers.clear();
        return this;
    }

    /**
     * Возвращает список значений для заданного заголовка.
     *
     * @param header имя заголовка.
     * @return список значений заголовка.
     */
    public List<String> get(String header) {
        return headers.getOrDefault(header, new ArrayList<>());
    }

    /**
     * Возвращает первое значение для заданного заголовка.
     *
     * @param header имя заголовка.
     * @return первое значение заголовка, или null, если заголовок не существует.
     */
    public String getFirst(String header) {
        Iterator<String> valuesIterator = get(header).iterator();
        return valuesIterator.hasNext() ? valuesIterator.next() : null;
    }

    /**
     * Класс Defaults содержит набор стандартных HTTP-заголовков.
     */
    public static final class Defaults {
        public static final String ACCEPT  = "Accept";
        public static final String ACCEPT_CHARSET = "Accept-Charset";
        public static final String ACCEPT_ENCODING = "Accept-Encoding";
        public static final String ACCEPT_LANGUAGE  = "Accept-Language";
        public static final String ACCEPT_RANGES = "Accept-Ranges";
        public static final String AGE  = "Age";
        public static final String ALLOW = "Allow";
        public static final String ALTERNATES  = "Alternates";
        public static final String AUTHORIZATION = "Authorization";
        public static final String CACHE_CONTROL  = "Cache-Control";
        public static final String CONNECTION  = "Connection";
        public static final String CONTENT_BASE = "Content-Base";
        public static final String CONTENT_DISPOSITION  = "Content-Disposition";
        public static final String CONTENT_ENCODING  = "Content-Encoding";
        public static final String CONTENT_LANGUAGE = "Content-Language";
        public static final String CONTENT_LENGTH  = "Content-Length";
        public static final String CONTENT_LOCATION = "Content-Location";
        public static final String CONTENT_MD5  = "Content-MD5";
        public static final String CONTENT_RANGE = "Content-Range";
        public static final String CONTENT_TYPE  = "Content-Type";
        public static final String CONTENT_VERSION = "Content-Version";
        public static final String DATE  = "Date";
        public static final String DERIVED_FROM = "Derived-From";
        public static final String ETAG  = "ETag";
        public static final String EXPECT = "Expect";
        public static final String EXPIRES  = "Expires";
        public static final String FROM = "From";
        public static final String HOST  = "Host";
        public static final String IF_MATCH = "If-Match";
        public static final String IF_MODIFIED_SINCE  = "If-Modified-Since";
        public static final String IF_NONE_MATCH = "If-None-Match";
        public static final String IF_RANGE  = "If-Range";
        public static final String IF_UNMODIFIED_SINCE = "If-Unmodified-Since";
        public static final String LAST_MODIFIED  = "Last-Modified";
        public static final String LINK = "Link";
        public static final String LOCATION  = "Location";
        public static final String MAX_FORWARDS = "Max-Forwards";
        public static final String MIME_VERSION  = "MIME-Version";
        public static final String PRAGMA = "Pragma";
        public static final String PROXY_AUTHENTICATE  = "Proxy-Authenticate";
        public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
        public static final String PUBLIC  = "Public";
        public static final String RANGE = "Range";
        public static final String REFERER  = "Referer";
        public static final String RETRY_AFTER = "Retry-After";
        public static final String SERVER  = "Server";
        public static final String TITLE = "Title";
        public static final String TE  = "TE";
        public static final String TRAILER = "Trailer";
        public static final String TRANSFER_ENCODING  = "Transfer-Encoding";
        public static final String UPGRADE = "Upgrade";
        public static final String URI  = "URI";
        public static final String USER_AGENT = "User-Agent";
        public static final String VARY  = "Vary";
        public static final String VIA = "Via";
        public static final String WARNING  = "Warning";
        public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    }
}
