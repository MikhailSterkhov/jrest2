package com.jrest.http.client.request;

import com.jrest.http.api.HttpClientConnection;
import com.jrest.mvc.model.Content;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import lombok.Builder;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutorService;

public class URLClientRequest extends AbstractClientHttpRequest {

    private static final int CONNECT_TIMEOUT_DEF = 1000;
    private static final int READ_TIMEOUT_DEF = 3000;

    @Builder
    protected URLClientRequest(HttpRequest httpRequest, ExecutorService executorService) {
        super(httpRequest, executorService);
    }

    @Override
    public Optional<HttpResponse> execute() {
        HttpClientConnection httpClientConnection = prepare(getHttpRequest());
        HttpResponse httpResponse = httpClientConnection.executeRequest();

        return Optional.of(httpResponse);
    }

    private HttpClientConnection prepare(HttpRequest httpRequest) {
        String fullUrl =
                Optional.ofNullable(httpRequest.getUrl()).orElse("") +
                        Optional.ofNullable(httpRequest.getUri()).orElse("");

        String outputString = Optional.ofNullable(httpRequest.getContent())
                .map(Content::getHyperText)
                .orElse(null);

        Map<String, List<String>> headersMap = Optional.ofNullable(httpRequest.getHeaders())
                .map(Headers::getHeaders).orElse(null);

        return HttpClientConnection.builder()
                .url(fullUrl)
                .output(outputString)
                .headers(headersMap)
                .charset(StandardCharsets.UTF_8)
                .connectTimeout(CONNECT_TIMEOUT_DEF)
                .readTimeout(READ_TIMEOUT_DEF)
                .method(httpRequest.getMethod().getName())
                .build();
    }
}
