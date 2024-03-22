package com.jrest.http.server.test.server;

import com.jrest.http.server.HttpServer;
import com.jrest.http.server.test.server.type.HttpRestServerTest;

import java.net.InetSocketAddress;

public class HttpRestServerStartTest {

    public static void main(String[] args) {
        HttpServer httpServer = HttpServer.builder()
                .socketAddress(new InetSocketAddress(8080))
                .build();

        httpServer.registerRepository(new HttpRestServerTest());
        httpServer.bind();
    }
}
