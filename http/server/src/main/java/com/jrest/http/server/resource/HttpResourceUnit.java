package com.jrest.http.server.resource;

import com.jrest.http.api.HttpListener;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HttpResourceUnit {

    @EqualsAndHashCode.Include
    private final HttpResourcePath path;
    private final HttpListener listener;

    public boolean isExpected(String requestUri) {
        return path.isExpected(requestUri);
    }
}
