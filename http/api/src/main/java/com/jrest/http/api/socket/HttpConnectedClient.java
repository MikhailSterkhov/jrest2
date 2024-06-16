package com.jrest.http.api.socket;

import com.jrest.http.api.socket.codec.SocketCodec;
import com.jrest.mvc.model.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HttpConnectedClient {

    private final Socket socket;
    private final SocketCodec codec;
    private final HttpServerSocketConfig config;

    public HttpConnectedClient(Socket socket, SocketCodec codec, HttpServerSocketConfig config) {
        this.socket = socket;
        this.codec = codec;
        this.config = config;
    }

    private void prepareHeaders(HttpResponse httpResponse) {
        Headers headers = httpResponse.getHeaders();
        if (headers == null) {
            httpResponse.setHeaders(headers = Headers.newHeaders());
        }

        if (!headers.has(Headers.Def.VARY)) {
            headers.set(Headers.Def.VARY, "Accept-Encoding");
        }

        if (!headers.has(Headers.Def.TRANSFER_ENCODING)) {
            if (config.getProtocol() == HttpProtocol.HTTP_1_1) {
                headers.set(Headers.Def.TRANSFER_ENCODING, "chunked");
            }
        }

        if (!headers.has(Headers.Def.SERVER)) {
            headers.add(Headers.Def.SERVER, String.format("Java/%s", System.getProperty("java.version")));
        }

        if (!headers.has(Headers.Def.CONNECTION)) {
            if (config.isKeepAlive()) {
                headers.add(Headers.Def.CONNECTION, "keep-alive");
            } else {
                headers.add(Headers.Def.CONNECTION, "close");
            }
        }

        Content content = httpResponse.getContent();
        if (content != null) {
            if (!headers.has(Headers.Def.CONTENT_TYPE)) {
                headers.add(Headers.Def.CONTENT_TYPE, content.getContentType());
            }
            if (!headers.has(Headers.Def.CONTENT_LENGTH)) {
                headers.add(Headers.Def.CONTENT_LENGTH, content.getContentLength());
            }
        }
    }

    public void sendResponse(HttpResponse response) throws IOException {
        prepareHeaders(response);

        ByteArrayOutputStream encodedResponse = codec.encode1(response);

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(encodedResponse.toByteArray());
        outputStream.flush();
    }
}
