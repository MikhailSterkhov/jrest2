package com.jrest.http.server.repository;

import com.jrest.mvc.model.authentication.HttpAuthenticator;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class HttpAuthorizationHandler {

    private final HttpAuthenticator authenticator;
    private final boolean isAsynchronous;
}
