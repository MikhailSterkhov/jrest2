package com.jrest.http.server.repository;

import com.jrest.http.api.HttpListener;
import com.jrest.mvc.model.HttpMethod;
import com.jrest.mvc.model.HttpRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class HttpRepositoryHandler {

    private final String uri;
    private final HttpMethod httpMethod;
    private final HttpListener invocation;
    private final boolean isAsynchronous;

    public boolean canProcess(HttpRequest httpRequest) {
        return (httpMethod == HttpMethod.ALL || httpRequest.getMethod().equals(getHttpMethod()));
    }
}
