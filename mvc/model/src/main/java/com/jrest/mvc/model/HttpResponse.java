package com.jrest.mvc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpResponse {

    public static final HttpResponse SKIP_ACTION = null;

    private final ResponseCode code;

    private final Headers headers;
    private final Content content;
}
