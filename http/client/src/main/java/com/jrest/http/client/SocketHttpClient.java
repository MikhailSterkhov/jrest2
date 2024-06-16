package com.jrest.http.client;

import com.jrest.http.client.request.SocketClientRequest;
import com.jrest.mvc.model.HttpProtocol;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;

import java.util.concurrent.ExecutorService;

public class SocketHttpClient extends AbstractHttpClient {

    private final HttpProtocol protocol;
    private final Integer connectTimeout;
    private final Boolean keepAlive;

    @Builder
    protected SocketHttpClient(HttpProtocol protocol, ExecutorService executorService, Integer connectTimeout, Boolean keepAlive) {
        super(executorService);
        this.protocol = protocol;
        this.connectTimeout = connectTimeout;
        this.keepAlive = keepAlive;
    }

    @Override
    public ClientHttpRequest create(HttpRequest httpRequest) {
        return SocketClientRequest.builder()
                .protocol(protocol)
                .timeout(connectTimeout)
                .keepAlive(keepAlive)
                .executorService(executorService)
                .httpRequest(httpRequest)
                .build();
    }
}
