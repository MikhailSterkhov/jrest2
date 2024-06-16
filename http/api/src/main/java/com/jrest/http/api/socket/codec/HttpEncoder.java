package com.jrest.http.api.socket.codec;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

import java.io.ByteArrayOutputStream;

public interface HttpEncoder extends StreamCodec {

    ByteArrayOutputStream encode0(HttpRequest httpRequest);

    ByteArrayOutputStream encode1(HttpResponse httpResponse);
}
