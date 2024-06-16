package com.jrest.http.server.resource;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Класс для управления ресурсами HTTP-сервера.
 * Содержит список зарегистрированных ресурсов и методы для их добавления и удаления.
 */
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServerResources {

    /**
     * Создает новый экземпляр {@code HttpServerResources}.
     *
     * @return новый экземпляр {@code HttpServerResources}
     */
    public static HttpServerResources create() {
        return new HttpServerResources();
    }

    /**
     * Список ресурсов сервера.
     */
    private final List<HttpResourceUnit> units = new CopyOnWriteArrayList<>();

    /**
     * Регистрирует новый ресурс на сервере.
     *
     * @param resourceUnit ресурс для регистрации
     */
    public void register(HttpResourceUnit resourceUnit) {
        units.add(resourceUnit);
    }

    /**
     * Отменяет регистрацию ресурса на сервере.
     *
     * @param resourceUnit ресурс для удаления
     */
    public void unregister(HttpResourceUnit resourceUnit) {
        units.remove(resourceUnit);
    }

    /**
     * Возвращает список всех зарегистрированных ресурсов.
     *
     * @return список всех ресурсов
     */
    public List<HttpResourceUnit> getAllResourcesUnits() {
        return Collections.unmodifiableList(units);
    }
}
