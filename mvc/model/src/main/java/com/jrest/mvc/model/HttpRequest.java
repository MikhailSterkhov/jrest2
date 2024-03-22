package com.jrest.mvc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpRequest {

    private final String url, uri;
    private final ContentBody contentBody;
    private final Headers headers;
    private final Attributes attributes;
    private final HttpMethod method;
}
