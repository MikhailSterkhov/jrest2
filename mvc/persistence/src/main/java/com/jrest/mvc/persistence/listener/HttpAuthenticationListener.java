package com.jrest.mvc.persistence.listener;

import com.jrest.mvc.model.HttpAuthenticationRequest;
import com.jrest.mvc.model.HttpAuthenticationResponse;

public interface HttpAuthenticationListener {

    HttpAuthenticationResponse authenticate(HttpAuthenticationRequest request);
}
