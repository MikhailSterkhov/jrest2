package com.jrest.http.client;

import com.jrest.http.client.request.SocketClientRequest;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;

import java.util.concurrent.ExecutorService;

public class SocketHttpClient extends AbstractHttpClient {

    private final Integer connectTimeout;
    private final Boolean keepAlive;

    @Builder
    protected SocketHttpClient(ExecutorService executorService, Integer connectTimeout, Boolean keepAlive) {
        super(executorService);
        this.connectTimeout = connectTimeout;
        this.keepAlive = keepAlive;
    }

    @Override
    public ClientHttpRequest create(HttpRequest httpRequest) {
        return SocketClientRequest.builder()
                .timeout(connectTimeout)
                .keepAlive(keepAlive)
                .executorService(executorService)
                .httpRequest(httpRequest)
                .build();
    }
}
