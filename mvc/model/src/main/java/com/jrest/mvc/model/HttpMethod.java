package com.jrest.mvc.model;

import lombok.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Представляет HTTP метод запроса.
 * Предоставляет статические методы для создания распространенных HTTP методов.
 * Поддерживает проверки для различных свойств HTTP методов, таких как их тип базовых методов,
 * идемпотентность, необходимость тела запроса, кэшируемость и требование ответа.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpMethod {

    public static final HttpMethod UNKNOWN = fromName("");
    public static final HttpMethod ALL = fromName("*");

    public static final HttpMethod GET = fromName("GET");
    public static final HttpMethod POST = fromName("POST");
    public static final HttpMethod DELETE = fromName("DELETE");
    public static final HttpMethod PUT = fromName("PUT");
    public static final HttpMethod TRACE = fromName("TRACE");
    public static final HttpMethod CONNECT = fromName("CONNECT");
    public static final HttpMethod HEAD = fromName("HEAD");
    public static final HttpMethod OPTIONS = fromName("OPTIONS");
    public static final HttpMethod PATCH = fromName("PATCH");

    private static final Set<HttpMethod> BASED = new HashSet<>(Arrays.asList(GET, POST, DELETE, PUT, TRACE, CONNECT, HEAD, OPTIONS, PATCH));
    private static final Set<HttpMethod> IDEMPOTENT = new HashSet<>(Arrays.asList(GET, HEAD, PUT, DELETE, OPTIONS, TRACE));
    private static final Set<HttpMethod> NEEDS_BODY = new HashSet<>(Arrays.asList(POST, PUT, CONNECT, PATCH));
    private static final Set<HttpMethod> CACHEABLE = new HashSet<>(Arrays.asList(GET, HEAD, POST, PATCH));

    /**
     * Создает экземпляр HttpMethod на основе имени метода.
     *
     * @param name имя HTTP метода.
     * @return экземпляр HttpMethod с указанным именем.
     */
    public static HttpMethod fromName(String name) {
        return new HttpMethod(name.toUpperCase());
    }

    private final String name;

    /**
     * Проверяет, является ли HTTP метод базовым (из множества BASED).
     *
     * @return true, если метод является базовым, иначе false.
     */
    public boolean isHttpBase() {
        return BASED.contains(this);
    }

    /**
     * Проверяет, является ли HTTP метод идемпотентным (из множества IDEMPOTENT).
     *
     * @return true, если метод является идемпотентным, иначе false.
     */
    public boolean isIdempotent() {
        return IDEMPOTENT.contains(this);
    }

    /**
     * Проверяет, требует ли HTTP метод тела запроса (из множества NEEDS_BODY).
     *
     * @return true, если метод требует тела запроса, иначе false.
     */
    public boolean needsBody() {
        return NEEDS_BODY.contains(this);
    }

    /**
     * Проверяет, является ли HTTP метод кэшируемым (из множества CACHEABLE).
     *
     * @return true, если метод является кэшируемым, иначе false.
     */
    public boolean isCacheable() {
        return CACHEABLE.contains(this);
    }

    /**
     * Проверяет, требует ли HTTP метод ответа (не равен ли HEAD).
     *
     * @return true, если метод требует ответа, иначе false.
     */
    public boolean needsResponse() {
        return !Objects.equals(this, HEAD);
    }

    @Override
    public String toString() {
        return name;
    }
}
