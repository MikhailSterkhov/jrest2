package com.jrest.http.client;

import com.jrest.mvc.model.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

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
    public Optional<HttpResponse> executeGet(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeGet(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeGet(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeGet(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeGet(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeGet(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeDelete(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeDelete(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeDelete(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeDelete(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeDelete(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeDelete(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executePost(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePost(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executePost(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
                .headers(headers)
                .attributes(Attributes.newAttributes())
                .build());
    }

    @Override
    public Optional<HttpResponse> executePost(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePost(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePost(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executePut(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePut(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executePut(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePut(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePut(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePut(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeTrace(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeTrace(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeTrace(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeTrace(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeTrace(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeTrace(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeHead(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeHead(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeHead(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeHead(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeHead(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeHead(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeOptions(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeOptions(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeOptions(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeOptions(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeOptions(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeOptions(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeConnect(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeConnect(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executeConnect(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeConnect(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeConnect(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executeConnect(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executePatch(String url, Attributes attributes) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePatch(String url, Content content) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
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
    public Optional<HttpResponse> executePatch(String url, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePatch(String url, Attributes attributes, Content content) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePatch(String url, Attributes attributes, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public Optional<HttpResponse> executePatch(String url, Attributes attributes, Content content, Headers headers) {
        return execute(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncGet(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.GET)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncDelete(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.DELETE)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPost(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.POST)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPut(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PUT)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncTrace(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.TRACE)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncHead(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.HEAD)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncOptions(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.OPTIONS)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncConnect(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.CONNECT)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, Attributes attributes) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, Content content) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
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
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, Attributes attributes, Content content) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, Attributes attributes, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .headers(headers)
                .build());
    }

    @Override
    public CompletableFuture<HttpResponse> executeAsyncPatch(String url, Attributes attributes, Content content, Headers headers) {
        return executeAsync(HttpRequest.builder()
                .attributes(attributes)
                .method(HttpMethod.PATCH)
                .url(url)
                .content(content)
                .headers(headers)
                .build());
    }
}
