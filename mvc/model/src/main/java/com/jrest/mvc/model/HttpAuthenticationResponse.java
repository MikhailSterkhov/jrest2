package com.jrest.mvc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class HttpAuthenticationResponse {

    private final Headers headers;
    private final HttpAuthorizationResult result;
}
