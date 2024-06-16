package com.jrest.http.server.resource;

import com.jrest.http.api.HttpListener;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс для представления единицы ресурса HTTP.
 * Содержит путь ресурса и связанный с ним слушатель HTTP.
 */
@Getter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HttpResourceUnit {

    /**
     * Путь ресурса.
     */
    @EqualsAndHashCode.Include
    private final HttpResourcePath path;

    /**
     * Слушатель HTTP для этого ресурса.
     */
    private final HttpListener listener;

    /**
     * Проверяет, соответствует ли запрашиваемый URI пути этого ресурса.
     *
     * @param requestUri запрашиваемый URI
     * @return {@code true}, если запрашиваемый URI соответствует пути этого ресурса, иначе {@code false}
     */
    public boolean isExpected(String requestUri) {
        return path.isExpected(requestUri);
    }
}
