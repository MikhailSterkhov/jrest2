package com.jrest.http.api.socket.codec.v1_0;

import com.jrest.http.api.socket.codec.v1_1.HttpV1_1Decoder;
import com.jrest.mvc.model.HttpProtocol;

public class HttpV1_0Decoder extends HttpV1_1Decoder {

    @Override
    public HttpProtocol protocol() {
        return HttpProtocol.HTTP_1_0;
    }
}
