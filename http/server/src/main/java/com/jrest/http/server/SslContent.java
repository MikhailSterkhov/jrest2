package com.jrest.http.server;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class SslContent {

    private final String keystorePath;
    private final String keystorePassword;
    private final String keyPassword;
}
