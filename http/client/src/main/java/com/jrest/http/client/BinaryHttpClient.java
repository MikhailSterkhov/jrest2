package com.jrest.http.client;

import com.jrest.binary.CompletedBinary;
import com.jrest.binary.HttpClientProperties;
import com.jrest.binary.HttpRequestProperties;
import com.jrest.mvc.model.*;
import lombok.Builder;

import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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

    public Optional<HttpResponse> executeBinary(String name) {
        return execute(buildHttpRequest(name));
    }

    public CompletableFuture<HttpResponse> executeBinaryAsync(String name) {
        return executeAsync(buildHttpRequest(name));
    }

    private HttpRequest buildHttpRequest(String name) {
        Optional<HttpRequestProperties> requestOptional = binary.findRequest(name);
        if (!requestOptional.isPresent()) {
            return null;
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

    private Content buildContent(HttpRequestProperties requestProperties) {
        Content.ContentBuilder contentBuilder = Content.builder();
        Properties bodyProperties = requestProperties.getBody();

        String contentTypeString = bodyProperties.getProperty("type");
        if (contentTypeString != null) {
            contentBuilder = contentBuilder.contentType(ContentType.fromString(contentTypeString));
        }

        String contentLengthString = bodyProperties.getProperty("length");
        if (contentLengthString != null) {
            contentBuilder = contentBuilder.contentLength(Integer.parseInt(contentLengthString));
        }

        String contentTextString = bodyProperties.getProperty("content");
        if (contentTextString != null) {
            contentBuilder = contentBuilder.hyperText(contentTextString);
        }

        return contentBuilder.build();
    }

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
