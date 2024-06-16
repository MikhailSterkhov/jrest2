package com.jrest.http.api.socket;

import com.jrest.mvc.model.HttpProtocol;
import lombok.Builder;
import lombok.Getter;

import java.net.InetSocketAddress;

@Getter
@Builder
public class HttpServerSocketConfig {

    private final HttpProtocol protocol;
    private final InetSocketAddress address;
    private final boolean ssl;
    private final boolean keepAlive;
    private final String keystorePath;
    private final String keystorePassword;
    private final String keyPassword;

    public static HttpServerSocketConfig defaultConfig(int port) {
        return HttpServerSocketConfig.builder()
                .protocol(HttpProtocol.HTTP_1_1)
                .address(new InetSocketAddress(port))
                .ssl(false)
                .keepAlive(true)
                .build();
    }
}
