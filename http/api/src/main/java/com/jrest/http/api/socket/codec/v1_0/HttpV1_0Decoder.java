package com.jrest.http.api.socket.codec.v1_0;

import com.jrest.http.api.socket.codec.v1_1.HttpV1_1Decoder;
import com.jrest.mvc.model.Content;
import com.jrest.mvc.model.HttpProtocol;

import java.io.BufferedReader;
import java.util.stream.Collectors;

public class HttpV1_0Decoder extends HttpV1_1Decoder {

    @Override
    protected Content readChunkedContent(BufferedReader reader) {
        return Content.fromText(reader.lines().collect(Collectors.joining("\n")));
    }

    @Override
    public HttpProtocol protocol() {
        return HttpProtocol.HTTP_1_0;
    }
}
