package com.jrest.mvc.model;

import lombok.*;

import java.util.Optional;
import java.util.Properties;

/**
 * Класс Attributes представляет параметры, которые могут быть добавлены к URL в HTTP запросах.
 * Он предоставляет удобные методы для работы с параметрами в виде свойств (Properties).
 */
@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Attributes {

    /**
     * Создает новый объект Attributes.
     *
     * @return новый объект Attributes.
     */
    public static Attributes newAttributes() {
        return new Attributes();
    }

    private final Properties properties = new Properties();

    /**
     * Возвращает строковое значение параметра по его имени.
     *
     * @param name имя параметра.
     * @return Optional, содержащий строковое значение параметра, если он существует.
     */
    public Optional<String> getString(String name) {
        return Optional.ofNullable(properties.getProperty(name));
    }

    /**
     * Возвращает булево значение параметра по его имени.
     *
     * @param name имя параметра.
     * @return Optional, содержащий булево значение параметра, если он существует и может быть преобразован в boolean.
     */
    public Optional<Boolean> getBoolean(String name) {
        return getString(name).map(Boolean::parseBoolean);
    }

    /**
     * Возвращает целочисленное значение параметра по его имени.
     *
     * @param name имя параметра.
     * @return Optional, содержащий целочисленное значение параметра, если он существует и может быть преобразовано в int.
     */
    public Optional<Integer> getInteger(String name) {
        return getString(name).map(Integer::parseInt);
    }

    /**
     * Возвращает длинное целочисленное значение параметра по его имени.
     *
     * @param name имя параметра.
     * @return Optional, содержащий длинное целочисленное значение параметра, если он существует и может быть преобразовано в long.
     */
    public Optional<Long> getLong(String name) {
        return getString(name).map(Long::parseLong);
    }

    /**
     * Проверяет наличие параметра по его имени.
     *
     * @param name имя параметра.
     * @return true, если параметр с указанным именем существует, иначе false.
     */
    public boolean contains(String name) {
        return properties.getProperty(name) != null;
    }

    /**
     * Добавляет параметр с указанным именем и значением.
     *
     * @param name  имя параметра.
     * @param value значение параметра.
     * @return текущий объект Attributes.
     */
    public Attributes with(String name, Object value) {
        properties.setProperty(name, value.toString());
        return this;
    }
}
