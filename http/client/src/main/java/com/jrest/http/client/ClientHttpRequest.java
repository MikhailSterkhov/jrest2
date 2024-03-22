package com.jrest.http.client;

import com.jrest.mvc.model.*;

import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ClientHttpRequest extends Serializable {

    Attributes attributes();

    Headers headers();

    HttpMethod method();

    ContentBody body();

    Optional<HttpResponse> execute();

    CompletableFuture<HttpResponse> executeAsync();
}
