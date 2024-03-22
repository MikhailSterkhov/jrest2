package com.jrest.http.client;

import com.jrest.http.client.request.URLClientRequest;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;

import java.util.concurrent.ExecutorService;

public class URLHttpClient extends AbstractHttpClient {

    @Builder
    protected URLHttpClient(ExecutorService executorService) {
        super(executorService);
    }

    @Override
    public ClientHttpRequest create(HttpRequest httpRequest) {
        return URLClientRequest.builder()
                .executorService(executorService)
                .httpRequest(httpRequest)
                .build();
    }
}
