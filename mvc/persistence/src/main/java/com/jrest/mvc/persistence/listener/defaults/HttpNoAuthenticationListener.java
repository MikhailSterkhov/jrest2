package com.jrest.mvc.persistence.listener.defaults;

import com.jrest.mvc.model.HttpAuthenticationRequest;
import com.jrest.mvc.model.HttpAuthenticationResponse;
import com.jrest.mvc.model.HttpAuthorizationResult;
import com.jrest.mvc.persistence.listener.HttpAuthenticationListener;

public class HttpNoAuthenticationListener implements HttpAuthenticationListener {

    @Override
    public HttpAuthenticationResponse authenticate(HttpAuthenticationRequest request) {
        return HttpAuthenticationResponse.builder().result(HttpAuthorizationResult.SUCCESS)
                .build();
    }
}
