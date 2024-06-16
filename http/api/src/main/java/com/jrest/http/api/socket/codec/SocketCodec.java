package com.jrest.http.api.socket.codec;

import com.jrest.http.api.socket.codec.v1_0.HttpV1_0Decoder;
import com.jrest.http.api.socket.codec.v1_0.HttpV1_0Encoder;
import com.jrest.http.api.socket.codec.v1_1.HttpV1_1Decoder;
import com.jrest.http.api.socket.codec.v1_1.HttpV1_1Encoder;
import com.jrest.mvc.model.HttpProtocol;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class SocketCodec {

    private static final Map<HttpProtocol, HttpDecoder> decoders = new HashMap<>();
    private static final Map<HttpProtocol, HttpEncoder> encoders = new HashMap<>();

    static {
        decoders.put(HttpProtocol.HTTP_1_0, new HttpV1_0Decoder());
        encoders.put(HttpProtocol.HTTP_1_0, new HttpV1_0Encoder());

        decoders.put(HttpProtocol.HTTP_1_1, new HttpV1_1Decoder());
        encoders.put(HttpProtocol.HTTP_1_1, new HttpV1_1Encoder());
    }

    private final HttpProtocol httpProtocol;

    private HttpDecoder getDecoder() {
        return decoders.get(httpProtocol);
    }

    private HttpEncoder getEncoder() {
        return encoders.get(httpProtocol);
    }

    public HttpRequest decode0(InputStream inputStream) {
        HttpDecoder decoder = getDecoder();
        if (decoder == null) {
            throw  new HttpCodecException(httpProtocol + " decoder is not registered");
        }
        return decoder.decode0(inputStream);
    }

    public HttpResponse decode1(InputStream inputStream) {
        HttpDecoder decoder = getDecoder();
        if (decoder == null) {
            throw new HttpCodecException(httpProtocol + " decoder is not registered");
        }
        return decoder.decode1(inputStream);
    }

    public ByteArrayOutputStream encode0(HttpRequest httpRequest) {
        HttpEncoder encoder = getEncoder();
        if (encoder == null) {
            throw  new HttpCodecException(httpProtocol + " encoder is not registered");
        }
        return encoder.encode0(httpRequest);
    }

    public ByteArrayOutputStream encode1(HttpResponse httpResponse) {
        HttpEncoder encoder = getEncoder();
        if (encoder == null) {
            throw new HttpCodecException(httpProtocol + " encoder is not registered");
        }
        return encoder.encode1(httpResponse);
    }
}
