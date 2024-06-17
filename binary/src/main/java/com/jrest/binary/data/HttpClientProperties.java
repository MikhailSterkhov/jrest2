package com.jrest.binary.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.Properties;

/**
 * Класс для хранения свойств HTTP клиента.
 * Обеспечивает доступ к свойствам в формате {@link Properties}.
 */
@Getter
@ToString
@RequiredArgsConstructor
public class HttpClientProperties {

    /**
     * Ключи для доступа к основным свойствам HTTP клиента.
     */
    public static final class Key {
        public static final String HOST = "host";
    }

    private final Properties properties;

    /**
     * Получает значение свойства в виде строки.
     *
     * @param property имя свойства
     * @return значение свойства или {@code null}, если свойство отсутствует
     */
    public String getString(String property) {
        return properties.getProperty(property);
    }

    /**
     * Получает значение свойства в виде целого числа.
     *
     * @param property имя свойства
     * @return значение свойства или {@code null}, если свойство отсутствует или не является целым числом
     */
    public Integer getInteger(String property) {
        return Optional.ofNullable(getString(property)).map(Integer::parseInt)
                .orElse(null);
    }

    /**
     * Получает значение свойства в виде логического значения.
     *
     * @param property имя свойства
     * @return значение свойства или {@code null}, если свойство отсутствует или не является логическим значением
     */
    public Boolean getBoolean(String property) {
        return Optional.ofNullable(getString(property)).map(Boolean::parseBoolean)
                .orElse(null);
    }
}
