package com.jrest.mvc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class HttpAuthenticationRequest {

    private final Date date;
    private final HttpRequest httpRequest;
}
