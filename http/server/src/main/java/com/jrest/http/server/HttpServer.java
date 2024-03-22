package com.jrest.http.server;

import com.jrest.http.api.HttpListener;
import lombok.Builder;
import lombok.ToString;

import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;

@Builder
@ToString
public class HttpServer {

    private final InetSocketAddress socketAddress;
    private final ExecutorService executorService;

    public void bindAt(Object proxy) {
        // todo
        bind();
    }

    public void bind() {
        // todo
    }

    public void register(HttpListener listener) {

    }
}
