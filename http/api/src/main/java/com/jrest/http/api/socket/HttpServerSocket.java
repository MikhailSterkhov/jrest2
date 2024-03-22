package com.jrest.http.api.socket;

import lombok.Builder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public final class HttpServerSocket {

    private final ServerSocketChannel channel;
    private final ExecutorService executorService;

    @Builder
    private HttpServerSocket(InetSocketAddress address, ExecutorService executorService) throws IOException {
        this.channel = ServerSocketChannel.open();
        this.executorService = executorService;

        channel.bind(address);
    }

    public synchronized CompletableFuture<HttpSocket> awaitNewCompletion() {
        return CompletableFuture.supplyAsync(this::await, executorService);
    }

    private HttpSocket await() {
        try {
            return new HttpSocket(channel.accept());
        } catch (IOException exception) {
            throw new HttpSocketException(exception);
        }
    }
}
