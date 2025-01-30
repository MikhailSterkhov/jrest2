package com.jrest.http.api.socket.codec.v1_0;

import com.jrest.http.api.socket.codec.v1_1.HttpV1_1Encoder;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpProtocol;
import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

import java.io.ByteArrayOutputStream;

public class HttpV1_0Encoder extends HttpV1_1Encoder {

    private void removeChunkedTransferEncoding(Headers headers) {
        headers.remove(Headers.Def.TRANSFER_ENCODING, "chunked");
    }

    @Override
    public ByteArrayOutputStream encode0(HttpRequest httpRequest) {
        removeChunkedTransferEncoding(httpRequest.getHeaders());
        return super.encode0(httpRequest);
    }

    @Override
    public ByteArrayOutputStream encode1(HttpResponse httpResponse) {
        removeChunkedTransferEncoding(httpResponse.getHeaders());
        return super.encode1(httpResponse);
    }

    @Override
    public HttpProtocol protocol() {
        return HttpProtocol.HTTP_1_0;
    }
}
