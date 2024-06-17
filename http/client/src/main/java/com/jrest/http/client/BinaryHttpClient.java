package com.jrest.http.client;

import com.jrest.binary.data.CompletedBinary;
import com.jrest.binary.data.HttpClientProperties;
import com.jrest.binary.data.HttpRequestProperties;
import com.jrest.mvc.model.*;
import lombok.Builder;

import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Implementation of an HTTP client that uses binary configuration for requests.
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
     * Executes a binary HTTP request by name.
     *
     * @param name the name of the request configuration.
     * @return an optional HttpResponse.
     */
    public Optional<HttpResponse> executeBinary(String name) {
        return execute(buildHttpRequest(name, null));
    }

    /**
     * Executes a binary HTTP request asynchronously by name.
     *
     * @param name the name of the request configuration.
     * @return a CompletableFuture of HttpResponse.
     */
    public CompletableFuture<HttpResponse> executeBinaryAsync(String name) {
        return executeAsync(buildHttpRequest(name, null));
    }

    /**
     * Executes a binary HTTP request by name with additional attributes.
     *
     * @param name  the name of the request configuration.
     * @param input the additional attributes to include in the request.
     * @return an optional HttpResponse.
     */
    public Optional<HttpResponse> executeBinary(String name, Attributes input) {
        Properties inputProperties = input.getProperties();
        return execute(buildHttpRequest(name, inputProperties));
    }

    /**
     * Executes a binary HTTP request asynchronously by name with additional attributes.
     *
     * @param name  the name of the request configuration.
     * @param input the additional attributes to include in the request.
     * @return a CompletableFuture of HttpResponse.
     */
    public CompletableFuture<HttpResponse> executeBinaryAsync(String name, Attributes input) {
        Properties inputProperties = input.getProperties();
        return executeAsync(buildHttpRequest(name, inputProperties));
    }

    /**
     * Builds an HttpRequest from the binary configuration.
     *
     * @param name            the name of the request configuration.
     * @param inputProperties additional properties to include in the request.
     * @return the constructed HttpRequest.
     */
    private HttpRequest buildHttpRequest(String name, Properties inputProperties) {
        CompletedBinary binary = this.binary;
        if (inputProperties != null && !inputProperties.isEmpty()) {
            binary = binary.copy(inputProperties);
        }

        Optional<HttpRequestProperties> requestOptional = binary.findRequest(name);
        if (!requestOptional.isPresent()) {
            return null; // Consider throwing an exception or returning an Optional.
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
     * Builds the content for the HttpRequest.
     *
     * @param requestProperties the properties of the request.
     * @return the constructed Content.
     */
    private Content buildContent(HttpRequestProperties requestProperties) {
        Content.ContentBuilder contentBuilder = Content.builder();
        Properties bodyProperties = requestProperties.getBody();

        String contentTypeString = bodyProperties.getProperty("type");
        if (contentTypeString != null) {
            contentBuilder.contentType(ContentType.fromString(contentTypeString));
        }

        String contentLengthString = bodyProperties.getProperty("length");
        if (contentLengthString != null) {
            contentBuilder.contentLength(Integer.parseInt(contentLengthString));
        }

        String contentTextString = bodyProperties.getProperty("content");
        if (contentTextString != null) {
            contentBuilder.hyperText(contentTextString);
        }

        return contentBuilder.build();
    }

    /**
     * Builds the query string for the HttpRequest.
     *
     * @param requestProperties the properties of the request.
     * @return the constructed query string.
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
