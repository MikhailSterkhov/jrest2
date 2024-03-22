package com.jrest.http.client;

import com.jrest.http.client.request.SocketClientRequest;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;

import java.util.concurrent.ExecutorService;

public class SocketHttpClient extends AbstractHttpClient {

    @Builder
    protected SocketHttpClient(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    public ClientHttpRequest create(HttpRequest httpRequest) {
        return SocketClientRequest.builder()
                .executorService(executorService)
                .httpRequest(httpRequest)
                .build();
    }
}
