package com.jrest.http.client;

import com.jrest.mvc.model.ContentBody;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface HttpClient extends Serializable {

    ClientHttpRequest create(HttpRequest httpRequest);

    Optional<HttpResponse> execute(HttpRequest httpRequest);

    CompletableFuture<HttpResponse> executeAsync(HttpRequest httpRequest);

    //

    Optional<HttpResponse> executeGet(String url);

    Optional<HttpResponse> executeGet(String url, ContentBody contentBody);

    Optional<HttpResponse> executeGet(String url, Headers headers);

    Optional<HttpResponse> executeGet(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executeDelete(String url);

    Optional<HttpResponse> executeDelete(String url, ContentBody contentBody);

    Optional<HttpResponse> executeDelete(String url, Headers headers);

    Optional<HttpResponse> executeDelete(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executePost(String url);

    Optional<HttpResponse> executePost(String url, ContentBody contentBody);

    Optional<HttpResponse> executePost(String url, Headers headers);

    Optional<HttpResponse> executePost(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executePut(String url);

    Optional<HttpResponse> executePut(String url, ContentBody contentBody);

    Optional<HttpResponse> executePut(String url, Headers headers);

    Optional<HttpResponse> executePut(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executeTrace(String url);

    Optional<HttpResponse> executeTrace(String url, ContentBody contentBody);

    Optional<HttpResponse> executeTrace(String url, Headers headers);

    Optional<HttpResponse> executeTrace(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executeHead(String url);

    Optional<HttpResponse> executeHead(String url, ContentBody contentBody);

    Optional<HttpResponse> executeHead(String url, Headers headers);

    Optional<HttpResponse> executeHead(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executeOptions(String url);

    Optional<HttpResponse> executeOptions(String url, ContentBody contentBody);

    Optional<HttpResponse> executeOptions(String url, Headers headers);

    Optional<HttpResponse> executeOptions(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executeConnect(String url);

    Optional<HttpResponse> executeConnect(String url, ContentBody contentBody);

    Optional<HttpResponse> executeConnect(String url, Headers headers);

    Optional<HttpResponse> executeConnect(String url, ContentBody contentBody, Headers headers);

    //

    Optional<HttpResponse> executePatch(String url);

    Optional<HttpResponse> executePatch(String url, ContentBody contentBody);

    Optional<HttpResponse> executePatch(String url, Headers headers);

    Optional<HttpResponse> executePatch(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncGet(String url);

    CompletableFuture<HttpResponse> executeAsyncGet(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncGet(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncGet(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncDelete(String url);

    CompletableFuture<HttpResponse> executeAsyncDelete(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncDelete(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncDelete(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncPost(String url);

    CompletableFuture<HttpResponse> executeAsyncPost(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncPost(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncPost(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncPut(String url);

    CompletableFuture<HttpResponse> executeAsyncPut(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncPut(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncPut(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncTrace(String url);

    CompletableFuture<HttpResponse> executeAsyncTrace(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncTrace(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncTrace(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncHead(String url);

    CompletableFuture<HttpResponse> executeAsyncHead(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncHead(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncHead(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncOptions(String url);

    CompletableFuture<HttpResponse> executeAsyncOptions(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncOptions(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncOptions(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncConnect(String url);

    CompletableFuture<HttpResponse> executeAsyncConnect(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncConnect(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncConnect(String url, ContentBody contentBody, Headers headers);

    //

    CompletableFuture<HttpResponse> executeAsyncPatch(String url);

    CompletableFuture<HttpResponse> executeAsyncPatch(String url, ContentBody contentBody);

    CompletableFuture<HttpResponse> executeAsyncPatch(String url, Headers headers);

    CompletableFuture<HttpResponse> executeAsyncPatch(String url, ContentBody contentBody, Headers headers);
}
