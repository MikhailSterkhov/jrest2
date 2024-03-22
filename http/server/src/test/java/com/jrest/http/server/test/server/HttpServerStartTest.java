package com.jrest.http.server.test.server;

import com.jrest.http.server.HttpServer;

import java.net.InetSocketAddress;

public class HttpServerStartTest {

    public static void main(String[] args) {
        HttpServer httpServer = HttpServer.builder()
                .socketAddress(new InetSocketAddress(8080))
                .build();
    }
}
