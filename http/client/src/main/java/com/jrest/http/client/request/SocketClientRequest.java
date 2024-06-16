package com.jrest.http.client.request;

import com.jrest.http.api.socket.HttpClientSocketChannel;
import com.jrest.mvc.model.HttpProtocol;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import lombok.Builder;

import java.util.Optional;
import java.util.concurrent.ExecutorService;

public class SocketClientRequest extends AbstractClientHttpRequest {

    private final Integer timeout;
    private final Boolean keepAlive;

    @Builder
    protected SocketClientRequest(HttpProtocol protocol, HttpRequest httpRequest, ExecutorService executorService, Integer timeout, Boolean keepAlive) {
        super(protocol, httpRequest, executorService);
        this.timeout = timeout;
        this.keepAlive = keepAlive;
    }

    @Override
    public Optional<HttpResponse> execute() {
        HttpRequest httpRequest = getHttpRequest();
        HttpProtocol httpProtocol = getProtocol();

        HttpClientSocketChannel socketChannel = HttpClientSocketChannel.fromUrl(
                httpProtocol,
                httpRequest.getUrl(),
                Optional.ofNullable(keepAlive).orElse(true),
                Optional.ofNullable(timeout).orElse(5000));

        HttpResponse httpResponse = socketChannel.sendRequest(httpRequest);
        return Optional.of(httpResponse);
    }
}
