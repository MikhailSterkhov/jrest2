package com.jrest.http.client.request;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import lombok.Builder;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

public class SocketClientRequest extends AbstractClientHttpRequest {

    @Builder
    protected SocketClientRequest(HttpRequest httpRequest, ExecutorService executorService) {
        super(httpRequest, executorService);
    }

    @Override
    public Optional<HttpResponse> execute() {
        HttpRequest httpRequest = getHttpRequest();
        return Optional.empty();
    }
}
