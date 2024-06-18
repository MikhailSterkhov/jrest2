package com.jrest.http.client.impl;

import com.jrest.http.client.ClientHttpRequest;
import com.jrest.http.client.request.URLClientRequest;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;

import java.util.concurrent.ExecutorService;

public class DefaultHttpClient extends AbstractHttpClient {

    private final Integer connectTimeout;
    private final Integer readTimeout;

    @Builder
    protected DefaultHttpClient(ExecutorService executorService, Integer connectTimeout, Integer readTimeout) {
        super(executorService);
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    public ClientHttpRequest create(HttpRequest httpRequest) {
        return URLClientRequest.builder()
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
                .executorService(executorService)
                .httpRequest(httpRequest)
                .build();
    }
}
