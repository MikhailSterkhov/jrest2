package com.jrest.http.api.socket.codec;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

import java.io.InputStream;

public interface HttpDecoder extends StreamCodec {

    HttpRequest decode0(InputStream inputStream);

    HttpResponse decode1(InputStream inputStream);
}
