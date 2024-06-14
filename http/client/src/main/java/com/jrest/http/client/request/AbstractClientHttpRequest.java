package com.jrest.http.client.request;

import com.jrest.http.client.ClientHttpRequest;
import com.jrest.http.client.HttpClientException;
import com.jrest.mvc.model.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractClientHttpRequest implements ClientHttpRequest {

    private final HttpRequest httpRequest;
    private final ExecutorService executorService;

    @Override
    public Attributes attributes() {
        return httpRequest.getAttributes();
    }

    @Override
    public Headers headers() {
        return httpRequest.getHeaders();
    }

    @Override
    public HttpMethod method() {
        return httpRequest.getMethod();
    }

    @Override
    public Content body() {
        return httpRequest.getContent();
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsync() {
        if (executorService == null) {
            throw new HttpClientException("Can`t to use executeAsync() because executorService is null");
        }
        return CompletableFuture.supplyAsync(() -> execute().orElseThrow(() ->
                        new HttpClientException("Failed async processing " + httpRequest.getUrl())),
                executorService);
    }
}
