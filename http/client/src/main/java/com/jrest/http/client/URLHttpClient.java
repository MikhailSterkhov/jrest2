package com.jrest.http.client;

import com.jrest.http.client.request.URLClientRequest;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;

import java.util.concurrent.ExecutorService;

public class URLHttpClient extends AbstractHttpClient {

    private final Integer connectTimeout;
    private final Integer readTimeout;

    @Builder
    protected URLHttpClient(ExecutorService executorService, Integer connectTimeout, Integer readTimeout) {
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
