package com.jrest.http.client;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ExecutorService;

@UtilityClass
public class HttpClients {

    public HttpClient createSocketClient(ExecutorService executorService) {
        return SocketHttpClient.builder()
                .executorService(executorService)
                .build();
    }

    public HttpClient createSocketClient(ExecutorService executorService, boolean keepAlive) {
        return SocketHttpClient.builder()
                .executorService(executorService)
                .keepAlive(keepAlive)
                .build();
    }

    public HttpClient createSocketClient(ExecutorService executorService, int connectTimeout) {
        return SocketHttpClient.builder()
                .executorService(executorService)
                .connectTimeout(connectTimeout)
                .build();
    }

    public HttpClient createSocketClient(ExecutorService executorService, int connectTimeout, boolean keepAlive) {
        return SocketHttpClient.builder()
                .executorService(executorService)
                .connectTimeout(connectTimeout)
                .keepAlive(keepAlive)
                .build();
    }

    public HttpClient createSocketClient() {
        return createSocketClient(null);
    }

    public HttpClient createSocketClient(boolean keepAlive) {
        return SocketHttpClient.builder()
                .keepAlive(keepAlive)
                .build();
    }

    public HttpClient createSocketClient(int connectTimeout) {
        return SocketHttpClient.builder()
                .connectTimeout(connectTimeout)
                .build();
    }

    public HttpClient createSocketClient(int connectTimeout, boolean keepAlive) {
        return SocketHttpClient.builder()
                .connectTimeout(connectTimeout)
                .keepAlive(keepAlive)
                .build();
    }

    public HttpClient createClient(ExecutorService executorService) {
        return URLHttpClient.builder()
                .executorService(executorService)
                .build();
    }

    public HttpClient createClient() {
        return createClient(null);
    }
}
