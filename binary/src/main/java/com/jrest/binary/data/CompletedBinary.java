package com.jrest.binary.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.*;

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

    /**
     * Скопировать текущие бинарные данные для указанных входящих
     * свойств, предварительно применив ожидающие плейсхолдеры
     * к новым input значениям.
     *
     * @param inputProperties входящие свойства
     * @return скопированные бинарные данные
     */
    public CompletedBinary copy(Properties inputProperties) {
        HttpClientProperties client = new HttpClientProperties(getClient().getProperties());
        List<HttpRequestProperties> requests = new ArrayList<>(getRequests());

        Properties clientProperties = client.getProperties();
        clientProperties.forEach((o, o2) ->
                clientProperties.setProperty(o.toString(), replaceInput(inputProperties, o2.toString())));

        requests.replaceAll(requestProperties -> {
            Properties attributesProperties = requestProperties.getAttributes();
            attributesProperties.forEach((o, o2) ->
                    attributesProperties.setProperty(o.toString(), replaceInput(inputProperties, o2.toString())));

            Properties bodyProperties = requestProperties.getBody();
            bodyProperties.forEach((o, o2) ->
                    bodyProperties.setProperty(o.toString(), replaceInput(inputProperties, o2.toString())));

            Map<String, List<String>> headers = requestProperties.getHeaders();
            new HashMap<>(headers).forEach((s, strings) -> {
                strings.replaceAll(s1 -> replaceInput(inputProperties, s1));
                headers.put(s, strings);
            });

            return requestProperties;
        });

        return new CompletedBinary(client, requests);
    }

    /**
     * Заменяет все вхождения плейсхолдеров в строке их значениями из входящих свойств.
     *
     * @param value строка с плейсхолдерами
     * @param inputProperties входящие свойства
     * @return строка с замененными плейсхолдерами
     */
    private String replaceInput(Properties inputProperties, String value) {
        String res = value;
        for (Object key : inputProperties.keySet()) {
            String placeholder = String.format("${input.%s}", key);
            res = res.replace(placeholder, inputProperties.getProperty(key.toString()));
        }
        return res;
    }
}
