package com.jrest.mvc.model;

import lombok.*;

/**
 * Представляет заголовок Cache-Control для HTTP запросов и ответов.
 * Предоставляет статические методы для создания распространенных директив управления кэшем.
 */
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CacheControl {

    /**
     * Директива Cache-Control: no-cache.
     */
    public static final CacheControl NO_CACHE = from("no-cache");

    /**
     * Директива Cache-Control: no-store.
     */
    public static final CacheControl NO_STORE = from("no-store");

    /**
     * Директива Cache-Control: no-transform.
     */
    public static final CacheControl NO_TRANSFORM = from("no-transform");

    /**
     * Директива Cache-Control: only-if-cached.
     */
    public static final CacheControl ONLY_IF_CACHED = from("only-if-cached");

    /**
     * Директива Cache-Control: cache-extension.
     */
    public static final CacheControl CACHE_EXTENSION = from("cache-extension");

    /**
     * Директива Cache-Control: private.
     */
    public static final CacheControl PRIVATE = from("private");

    /**
     * Директива Cache-Control: public.
     */
    public static final CacheControl PUBLIC = from("public");

    /**
     * Создает экземпляр CacheControl с директивой max-age.
     *
     * @param maxAge максимальный возраст в секундах.
     * @return экземпляр CacheControl с директивой max-age.
     */
    public static CacheControl maxAge(int maxAge) {
        return from(String.format("max-age=%d", maxAge));
    }

    /**
     * Создает экземпляр CacheControl с директивой max-stale.
     *
     * @param maxStale максимальная устареваемость в секундах.
     * @return экземпляр CacheControl с директивой max-stale.
     */
    public static CacheControl maxStale(int maxStale) {
        return from(String.format("max-stale=%d", maxStale));
    }

    /**
     * Создает экземпляр CacheControl с директивой min-fresh.
     *
     * @param minFresh минимальная свежесть в секундах.
     * @return экземпляр CacheControl с директивой min-fresh.
     */
    public static CacheControl minFresh(int minFresh) {
        return from(String.format("min-fresh=%d", minFresh));
    }

    /**
     * Создает экземпляр CacheControl из произвольного строки.
     *
     * @param value произвольная директива Cache-Control.
     * @return экземпляр CacheControl с указанной директивой.
     */
    public static CacheControl from(String value) {
        return new CacheControl(value);
    }

    private final String value;

    @Override
    public String toString() {
        return value;
    }
}
