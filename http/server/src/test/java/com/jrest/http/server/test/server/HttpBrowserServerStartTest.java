package com.jrest.http.server.test.server;

import com.jrest.http.server.HttpServer;
import com.jrest.http.server.test.server.type.HttpBrowserServerTest;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpBrowserServerStartTest {

    public static void main(String[] args) {
        HttpServer httpServer = HttpServer.builder()
                .socketAddress(new InetSocketAddress(8080))
                .executorService(Executors.newCachedThreadPool()) // pass for access to @HttpAsync annotation
                .build();

        httpServer.bindAt(new HttpBrowserServerTest());
    }
}
