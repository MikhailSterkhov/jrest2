package com.jrest.http.api;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

public interface HttpListener {

    HttpResponse process(HttpRequest request);
}
