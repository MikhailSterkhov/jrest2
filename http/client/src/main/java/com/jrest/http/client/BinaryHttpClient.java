package com.jrest.http.client;

import com.jrest.binary.data.CompletedBinary;
import com.jrest.binary.data.HttpClientProperties;
import com.jrest.binary.data.HttpRequestProperties;
import com.jrest.mvc.model.*;
import com.jrest.mvc.model.util.ContentUtil;
import lombok.Builder;

import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Реализация HTTP-клиента, использующего бинарные конфигурации для запросов.
 */
public class BinaryHttpClient extends AbstractHttpClient {

    private final HttpClient httpClient;
    private final CompletedBinary binary;

    @Builder
    protected BinaryHttpClient(HttpClient httpClient, CompletedBinary binary) {
        super(null);
        this.httpClient = httpClient;
        this.binary = binary;
    }

    @Override
    public ClientHttpRequest create(HttpRequest httpRequest) {
        return httpClient.create(httpRequest);
    }

    /**
     * Выполняет бинарный HTTP-запрос по имени.
     *
     * @param name имя конфигурации запроса.
     * @return опциональный HttpResponse.
     */
    public Optional<HttpResponse> executeBinary(String name) {
        return execute(buildHttpRequest(name, null));
    }

    /**
     * Асинхронно выполняет бинарный HTTP-запрос по имени.
     *
     * @param name имя конфигурации запроса.
     * @return CompletableFuture с HttpResponse.
     */
    public CompletableFuture<HttpResponse> executeBinaryAsync(String name) {
        return executeAsync(buildHttpRequest(name, null));
    }

    /**
     * Выполняет бинарный HTTP-запрос по имени с дополнительными атрибутами.
     *
     * @param name  имя конфигурации запроса.
     * @param input дополнительные атрибуты для включения в запрос.
     * @return опциональный HttpResponse.
     */
    public Optional<HttpResponse> executeBinary(String name, Attributes input) {
        Properties inputProperties = input.getProperties();
        return execute(buildHttpRequest(name, inputProperties));
    }

    /**
     * Асинхронно выполняет бинарный HTTP-запрос по имени с дополнительными атрибутами.
     *
     * @param name  имя конфигурации запроса.
     * @param input дополнительные атрибуты для включения в запрос.
     * @return CompletableFuture с HttpResponse.
     */
    public CompletableFuture<HttpResponse> executeBinaryAsync(String name, Attributes input) {
        Properties inputProperties = input.getProperties();
        return executeAsync(buildHttpRequest(name, inputProperties));
    }

    /**
     * Создает HttpRequest из бинарной конфигурации.
     *
     * @param name            имя конфигурации запроса.
     * @param inputProperties дополнительные свойства для включения в запрос.
     * @return созданный HttpRequest.
     */
    private HttpRequest buildHttpRequest(String name, Properties inputProperties) {
        CompletedBinary binary = this.binary;
        if (inputProperties != null && !inputProperties.isEmpty()) {
            binary = binary.copy(inputProperties);
        }

        Optional<HttpRequestProperties> requestOptional = binary.findRequest(name);
        if (!requestOptional.isPresent()) {
            throw new HttpClientException("Unknown binary function `" + name + "`");
        }

        HttpRequestProperties requestProperties = requestOptional.get();

        String query = buildQuery(requestProperties);
        Content content = buildContent(requestProperties);

        return HttpRequest.builder()
                .content(content)
                .url(query)
                .attributes(Attributes.fromProperties(requestProperties.getAttributes()))
                .method(HttpMethod.fromName(requestProperties.getMethod()))
                .headers(Headers.fromMap(requestProperties.getHeaders()))
                .build();
    }

    /**
     * Создает контент для HttpRequest.
     *
     * @param requestProperties свойства запроса.
     * @return созданный Content.
     */
    private Content buildContent(HttpRequestProperties requestProperties) {
        Content.ContentBuilder contentBuilder = Content.builder();
        Properties bodyProperties = requestProperties.getBody();

        String contentTextString = bodyProperties.getProperty("text");
        if (contentTextString != null) {
            contentBuilder.text(contentTextString);

            String contentTypeString = bodyProperties.getProperty("type");
            if (contentTypeString != null) {
                contentBuilder.contentType(ContentType.fromString(contentTypeString));
            } else {
                contentBuilder.contentType(ContentType.DEFAULT_TEXT);
            }

            String contentLengthString = bodyProperties.getProperty("length");
            if (contentLengthString != null) {
                contentBuilder.contentLength(Integer.parseInt(contentLengthString));
            } else {
                contentBuilder.contentLength(ContentUtil.fromString(contentTextString).length);
            }
        }

        return contentBuilder.build();
    }

    /**
     * Создает строку запроса для HttpRequest.
     *
     * @param requestProperties свойства запроса.
     * @return созданная строка запроса.
     */
    private String buildQuery(HttpRequestProperties requestProperties) {
        Properties attributes = requestProperties.getAttributes();
        String attributesString = attributes.keySet()
                .stream()
                .map(Object::toString)
                .map(property -> String.format("%s=%s", property, attributes.getProperty(property)))
                .collect(Collectors.joining("&"));

        String hostString = binary.getClient().getString(HttpClientProperties.Key.HOST);
        if (hostString.endsWith("/")) {
            hostString = hostString.substring(0, hostString.length() - 1);
        }

        String uri = requestProperties.getUri();
        if (!uri.startsWith("/")) {
            uri = ("/" + uri);
        }

        return hostString + uri + (attributesString.isEmpty() ? "" : "?" + attributesString);
    }
}
