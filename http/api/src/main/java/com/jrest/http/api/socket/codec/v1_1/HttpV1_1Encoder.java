package com.jrest.http.api.socket.codec.v1_1;

import com.jrest.http.api.socket.codec.HttpCodecException;
import com.jrest.http.api.socket.codec.HttpEncoder;
import com.jrest.http.api.socket.codec.EncodingStream;
import com.jrest.mvc.model.*;

import java.io.*;

public class HttpV1_1Encoder implements HttpEncoder {

    @Override
    public ByteArrayOutputStream encode0(HttpRequest httpRequest) {
        try {
            EncodingStream encodingStream = new EncodingStream(protocol());

            encodingStream.writeStatusLine(httpRequest);
            encodingStream.writeHeaders(httpRequest.getHeaders());

            encodingStream.writeContent(httpRequest.getContent(), httpRequest.getHeaders());

            return encodingStream.toOutputStream();
        } catch (Throwable ex) {
            new HttpCodecException("failed request encode", ex).printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayOutputStream encode1(HttpResponse httpResponse) {
        try {
            EncodingStream encodingStream = new EncodingStream(protocol());

            encodingStream.writeStatusLine(httpResponse);
            encodingStream.writeHeaders(httpResponse.getHeaders());

            encodingStream.writeContent(httpResponse.getContent(), httpResponse.getHeaders());

            return encodingStream.toOutputStream();
        } catch (Throwable ex) {
            new HttpCodecException("failed response encode", ex).printStackTrace();
            return null;
        }
    }

    @Override
    public HttpProtocol protocol() {
        return HttpProtocol.HTTP_1_1;
    }
}
