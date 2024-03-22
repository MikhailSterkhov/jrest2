package com.jrest.http.api.socket;

import java.nio.channels.SocketChannel;

public class HttpSocket {

    private final SocketChannel channel;

    HttpSocket(SocketChannel channel) {
        this.channel = channel;
    }

    public synchronized HttpSocketInput read() {
        // ...
        return null;
    }

    public synchronized void write(HttpSocketOutput output) {
        // ...
    }

    public synchronized void writeAndFlush(HttpSocketOutput output) {
        write(output);
        // ...
    }
}
