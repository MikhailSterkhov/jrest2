package com.jrest.mvc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class HttpBrowserResponse {

    private final ResponseCode code;
    private final HttpBrowserView view;
}
