package com.jrest.http.api.socket;

import com.jrest.http.api.socket.codec.SocketCodec;
import com.jrest.mvc.model.util.InputStreamUtil;
import com.jrest.mvc.model.*;

import javax.net.ssl.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class HttpClientSocketChannel {

    private final SocketCodec codec;
    private final HttpClientSocketConfig config;

    public HttpClientSocketChannel(HttpClientSocketConfig config) {
        this.config = config;
        this.codec = new SocketCodec(config.getProtocol());
    }

    public static HttpClientSocketChannel fromUrl(HttpProtocol protocol, String urlString, boolean keepAlive, int timeout) {
        try {
            HttpClientSocketConfig config = HttpClientSocketConfig.fromUrl(
                    protocol, urlString, keepAlive, timeout);
            return new HttpClientSocketChannel(config);
        } catch (MalformedURLException exception) {
            throw new HttpSocketException(exception);
        }
    }

    private void prepareHeaders(HttpRequest httpRequest) {
        Headers headers = httpRequest.getHeaders();
        if (headers == null) {
            httpRequest.setHeaders(headers = Headers.newHeaders());
        }

        if (!headers.has(Headers.Def.USER_AGENT)) {
            headers.add(Headers.Def.USER_AGENT, String.format("Java/%s", System.getProperty("java.version")));
        }

        if (!headers.has(Headers.Def.HOST)) {
            if (config.isDefaultPort()) {
                headers.add(Headers.Def.HOST, config.getHost());
            } else {
                headers.add(Headers.Def.HOST, String.format("%s:%s", config.getHost(), config.getPort()));
            }
        }

        if (!headers.has(Headers.Def.ACCEPT)) {
            headers.add(Headers.Def.ACCEPT, "text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
        }

        if (!headers.has(Headers.Def.CONNECTION)) {
            if (config.isKeepAlive()) {
                headers.add(Headers.Def.CONNECTION, "keep-alive");
            } else {
                headers.add(Headers.Def.CONNECTION, "close");
            }
        }

        Content content = httpRequest.getContent();
        if (content != null) {
            if (!headers.has(Headers.Def.CONTENT_TYPE)) {
                headers.add(Headers.Def.CONTENT_TYPE, content.getContentType());
            }
            if (!headers.has(Headers.Def.CONTENT_LENGTH) && !headers.has(Headers.Def.TRANSFER_ENCODING, "chunked")) {
                headers.add(Headers.Def.CONTENT_LENGTH, content.getContentLength());
            }
        }
    }

    private void prepareAttributes(HttpRequest request) throws UnsupportedEncodingException {
        String url = request.getUrl();
        Attributes attributes = request.getAttributes();

        if (attributes == null) {
            return;
        }

        Properties attributesProperties = attributes.getProperties();

        if (!attributesProperties.isEmpty() && !url.contains("?")) {
            StringBuilder urlBuilder = new StringBuilder(url);
            urlBuilder.append("?");

            for (Map.Entry<Object, Object> entry : attributesProperties.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                urlBuilder.append(URLEncoder.encode(key, StandardCharsets.UTF_8.name()));
                urlBuilder.append("=");
                urlBuilder.append(URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
                urlBuilder.append("&");
            }

            // Remove the last '&' character
            urlBuilder.setLength(urlBuilder.length() - 1);
            request.setUrl(urlBuilder.toString());
        }
    }

    public HttpResponse sendRequest(HttpRequest request) {
        try {
            prepareHeaders(request);
            prepareAttributes(request);

            if (config.getPort() == 443) {
                return sendHttpsRequest(request);
            } else {
                return sendHttpRequest(request);
            }
        } catch (IOException exception) {
            throw new HttpSocketException(exception);
        }
    }

    private HttpResponse sendRequest(Socket socket, HttpRequest httpRequest) throws IOException {
        try {
            socket.connect(new InetSocketAddress(
                    config.getHost(),
                    config.getPort()));

            socket.setSoTimeout(config.getTimeout());

            // Encode request and send it
            try (ByteArrayOutputStream encodedRequest = codec.encode0(httpRequest)) {

                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(encodedRequest.toByteArray());
                outputStream.flush();
            }

            // Decode response
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

            int read = inputStream.read();
            byte[] bytesArray = InputStreamUtil.toBytesArray(inputStream);

            byteArrayOutputStream.write(read);
            byteArrayOutputStream.write(bytesArray);

            ByteArrayInputStream responseStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            return codec.decode1(responseStream);
        } catch (SocketException e) {
            throw new HttpSocketException("Socket error: " + e.getMessage(), e);
        } catch (SSLHandshakeException e) {
            throw new HttpSocketException("SSL handshake failed: " + e.getMessage(), e);
        } catch (EOFException e) {
            throw new HttpSocketException("Unexpected end of file from server", e);
        } catch (IOException e) {
            throw new HttpSocketException("I/O error: " + e.getMessage(), e);
        } catch (Throwable e) {
            throw new HttpSocketException("Failed to read: uncorrected input data", e);
        } finally {
            if (!config.isKeepAlive()) {
                socket.close();
            }
        }
    }

    private HttpResponse sendHttpRequest(HttpRequest request) throws IOException {
        return sendRequest(new Socket(), request);
    }

    private HttpResponse sendHttpsRequest(HttpRequest request) throws IOException {
        SSLSocket socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket();
        return sendRequest(socket, request);
    }
}
