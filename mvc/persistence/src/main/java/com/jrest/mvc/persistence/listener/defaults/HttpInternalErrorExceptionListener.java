package com.jrest.mvc.persistence.listener.defaults;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.model.ResponseCode;
import com.jrest.mvc.model.Content;
import com.jrest.mvc.persistence.listener.HttpExceptionListener;

public class HttpInternalErrorExceptionListener implements HttpExceptionListener {

    @Override
    public HttpResponse handle(HttpRequest request, Throwable exception) {
        return HttpResponse.builder()
                .code(ResponseCode.INTERNAL_ERROR)
                .content(Content.fromText(exception.toString()))
                .build();
    }
}
