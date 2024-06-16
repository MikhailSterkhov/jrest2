package com.jrest.http.api.socket;

import com.jrest.mvc.model.HttpProtocol;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Getter
@Builder
@ToString
public class HttpClientSocketConfig {

    private final HttpProtocol protocol;
    private final String host;
    private final int port;
    private final boolean keepAlive;
    private final int timeout;

    public boolean isDefaultPort() {
        return port == 80 || port == 443;
    }

    public static HttpClientSocketConfig fromUrl(HttpProtocol protocol, String urlString, boolean keepAlive, int timeout) throws MalformedURLException {
        URL url = new URL(urlString);
        String host = url.getHost();
        int port = url.getPort();
        if (port == -1) {
            port = url.getProtocol().equals("https") ? 443 : 80;
        }
        return HttpClientSocketConfig.builder()
                .protocol(protocol)
                .host(host)
                .port(port)
                .keepAlive(keepAlive)
                .timeout(timeout)
                .build();
    }
}
