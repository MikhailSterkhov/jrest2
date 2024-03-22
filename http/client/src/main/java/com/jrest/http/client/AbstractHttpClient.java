package com.jrest.http.client;

import com.jrest.mvc.model.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@ToString
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractHttpClient implements HttpClient {

    protected final ExecutorService executorService;

    @Override
    public synchronized Optional<HttpResponse> execute(HttpRequest httpRequest) {
        ClientHttpRequest clientHttpRequest = this.create(httpRequest);
        return clientHttpRequest.execute();
    }

    @Override
    public synchronized CompletableFuture<HttpResponse> executeAsync(HttpRequest httpRequest) {
        ClientHttpRequest clientHttpRequest = this.create(httpRequest);
        return clientHttpRequest.executeAsync();
    }

    //

    @Override
    public Optional<HttpResponse> executeGet(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeGet(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeGet(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeGet(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executeDelete(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeDelete(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeDelete(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeDelete(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executePost(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePost(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePost(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePost(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executePut(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePut(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePut(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePut(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executeTrace(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeTrace(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeTrace(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeTrace(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executeHead(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeHead(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeHead(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeHead(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executeOptions(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeOptions(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeOptions(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeOptions(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executeConnect(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeConnect(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeConnect(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeConnect(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public Optional<HttpResponse> executePatch(String url) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePatch(String url, ContentBody contentBody) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePatch(String url, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePatch(String url, ContentBody contentBody, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }

    //

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, ContentBody contentBody) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .contentBody(contentBody)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, ContentBody contentBody, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .contentBody(contentBody)
                .headers(headers)
                .build());
    }
}
