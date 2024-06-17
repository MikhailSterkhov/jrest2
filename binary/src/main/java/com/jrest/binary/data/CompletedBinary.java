package com.jrest.binary.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

/**
 * Класс для представления завершенной бинарной конфигурации HTTP клиента и запросов.
 */
@Getter
@ToString
@RequiredArgsConstructor
public class CompletedBinary {

    /**
     * Свойства HTTP клиента.
     */
    private final HttpClientProperties client;

    /**
     * Список свойств HTTP запросов.
     */
    private final List<HttpRequestProperties> requests;

    /**
     * Ищет HTTP запрос по имени.
     *
     * @param name имя HTTP запроса
     * @return {@code Optional} содержащий {@link HttpRequestProperties}, если запрос с заданным именем найден,
     * иначе пустой {@code Optional}
     */
    public Optional<HttpRequestProperties> findRequest(String name) {
        return requests.stream()
                .filter(httpRequestProperties -> httpRequestProperties.getName().equals(name))
                .findFirst();
    }
}
