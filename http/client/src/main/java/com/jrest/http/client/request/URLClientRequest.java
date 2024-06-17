package com.jrest.http.client.request;

import com.jrest.http.api.HttpClientConnection;
import com.jrest.mvc.model.*;
import lombok.Builder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

public class URLClientRequest extends AbstractClientHttpRequest {

    private static final int CONNECT_TIMEOUT_DEF = 1000;
    private static final int READ_TIMEOUT_DEF = 3000;

    private final Integer connectTimeout;
    private final Integer readTimeout;

    @Builder
    protected URLClientRequest(HttpRequest httpRequest, ExecutorService executorService,
                               Integer connectTimeout, Integer readTimeout) {

        super(HttpProtocol.HTTP_1_1, httpRequest, executorService);
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    @Override
    public Optional<HttpResponse> execute() {
        HttpClientConnection httpClientConnection = prepare(getHttpRequest());
        HttpResponse httpResponse = httpClientConnection.executeRequest();

        return Optional.of(httpResponse);
    }

    private HttpClientConnection prepare(HttpRequest httpRequest) {
        String outputString = Optional.ofNullable(httpRequest.getContent())
                .map(Content::getHyperText)
                .orElse(null);

        Map<String, List<String>> headersMap = Optional.ofNullable(httpRequest.getHeaders())
                .map(Headers::getMap).orElse(null);

        return HttpClientConnection.builder()
                .url(httpRequest.getUrl())
                .output(outputString)
                .headers(headersMap)
                .charset(StandardCharsets.UTF_8)
                .method(httpRequest.getMethod().getName())
                .connectTimeout(connectTimeout == null ? CONNECT_TIMEOUT_DEF : connectTimeout)
                .readTimeout(readTimeout == null ? READ_TIMEOUT_DEF : readTimeout)
                .build();
    }
}
