package com.jrest.mvc.persistence.listener;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

public interface HttpExceptionListener {

    HttpResponse handle(HttpRequest request, Throwable exception);
}
