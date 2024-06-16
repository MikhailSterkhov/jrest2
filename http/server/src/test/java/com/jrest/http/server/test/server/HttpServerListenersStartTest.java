package com.jrest.http.server.test.server;

import com.jrest.http.server.HttpServer;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.model.ResponseCode;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class HttpServerListenersStartTest {

    public static void main(String[] args) {
        HttpServer httpServer = HttpServer.builder()
                .socketAddress(new InetSocketAddress(8080))
                .executorService(Executors.newCachedThreadPool())
                .build();

        httpServer.registerAsyncListener(request -> {
            System.out.println(request);
            return HttpResponse.builder().code(ResponseCode.OK).build();
        });

        httpServer.bind();
    }
}
