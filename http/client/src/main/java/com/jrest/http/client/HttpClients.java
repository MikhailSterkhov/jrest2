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

    public HttpClient createSocketClient() {
        return createSocketClient(null);
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
