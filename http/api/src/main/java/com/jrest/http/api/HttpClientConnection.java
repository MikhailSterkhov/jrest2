package com.jrest.http.api;

import com.jrest.mvc.model.Content;
import com.jrest.mvc.model.Headers;
import com.jrest.mvc.model.HttpResponse;
import com.jrest.mvc.model.ResponseCode;
import com.jrest.mvc.model.util.InputStreamUtil;
import lombok.Builder;
import lombok.ToString;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@Builder
@ToString
public class HttpClientConnection {

    private final int readTimeout;
    private final int connectTimeout;
    private final Charset charset;
    private final String url;
    private final String method;
    private final String output;
    private final Map<String, List<String>> headers;

    public HttpResponse executeRequest() {
        final HttpURLConnection connection = prepareConnection();
        initOptions(connection);

        if (canWrite()) {
            writeBody(connection);
        }

        String responseBody = readBody(connection);
        try {
            return HttpResponse.builder()
                    .content(Content.fromText(responseBody))
                    .code(ResponseCode.fromCode(connection.getResponseCode()))
                    .headers(Headers.fromMap(connection.getHeaderFields()))
                    .build();
        } catch (IOException exception) {
            throw new HttpURLException(exception);
        }
    }

    private HttpURLConnection prepareConnection() {
        try {
            return (HttpURLConnection) new URL(url).openConnection();
        } catch (IOException exception) {
            throw new HttpURLException(exception);
        }
    }

    private boolean canWrite() {
        return output != null && !output.isEmpty();
    }

    private void writeBody(HttpURLConnection connection) {
        connection.setDoOutput(true);
        try {
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(output.getBytes(charset));

            outputStream.flush();

        } catch (IOException exception) {
            throw new HttpURLException(exception);
        }
    }

    private String readBody(HttpURLConnection connection) {
        try {
            return InputStreamUtil.toString(connection.getInputStream());
        } catch (IOException exception) {
            throw new HttpURLException(exception);
        }
    }

    private void initOptions(HttpURLConnection connection) {
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);

        try {
            connection.setRequestMethod(method);
        } catch (ProtocolException exception) {
            throw new HttpURLException(exception);
        }

        if (headers != null) {
            headers.forEach((header, values) -> {
                if (!values.isEmpty()) {
                    connection.setRequestProperty(header, values.get(0));
                }
            });
        }
    }
}
