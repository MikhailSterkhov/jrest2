package com.jrest.http.api.socket.codec.v1_1;

import com.jrest.http.api.socket.codec.HttpCodecException;
import com.jrest.http.api.socket.codec.HttpDecoder;
import com.jrest.mvc.model.*;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

public class HttpV1_1Decoder implements HttpDecoder {

    @Override
    public HttpRequest decode0(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            // Process the request line
            String[] statusLineParts = line.split(" ");
            HttpMethod method = HttpMethod.fromName(statusLineParts[0]);
            String url = statusLineParts[1];

            // Read headers
            Headers headers = Headers.newHeaders();
            while (!(line = reader.readLine()).isEmpty()) {
                String[] headerParts = line.split(": ", 2);
                headers.add(headerParts[0], headerParts[1]);
            }

            Attributes attributes = parseAttributes(url);
            Content content = readContent(reader, headers);

            return HttpRequest.builder()
                    .method(method)
                    .attributes(attributes)
                    .url(url)
                    .headers(headers)
                    .content(content)
                    .build();
        } catch (Throwable ex) {
            new HttpCodecException("failed request decode", ex).printStackTrace();
            return null;
        }
    }

    @Override
    public HttpResponse decode1(InputStream inputStream) {
        try {
            Headers headers = Headers.newHeaders();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            headers.set(null, line);

            // Process the status line
            String[] statusLineParts = line.split(" ", 3);
            HttpProtocol httpProtocol = HttpProtocol.read(statusLineParts[0]);
            ResponseCode code = ResponseCode.fromCodeAndMessage(Integer.parseInt(statusLineParts[1]), statusLineParts[2]);

            // Read headers
            while (!(line = reader.readLine()).isEmpty()) {
                String[] headerParts = line.split(": ", 2);
                headers.add(headerParts[0], headerParts[1]);
            }

            // Read body
            Content content = readContent(reader, headers);

            return HttpResponse.builder()
                    .code(code)
                    .protocol(httpProtocol)
                    .headers(headers)
                    .content(content)
                    .build();
        } catch (Throwable ex) {
            new HttpCodecException("failed response decode", ex).printStackTrace();
            return null;
        }
    }

    protected Content readContent(BufferedReader reader, Headers headers) throws IOException {
        String contentText;
        if (headers.has(Headers.Def.TRANSFER_ENCODING, "chunked")) {
            contentText = readChunkedContent(reader);
        } else {
            // Handle other encodings if necessary
            contentText = reader.lines().collect(Collectors.joining("\n"));
        }
        return Content.builder()
                .text(contentText)
                .contentLength(Optional.ofNullable(headers.getLast(Headers.Def.CONTENT_LENGTH))
                        .map(Integer::parseInt)
                        .orElse(Optional.ofNullable(contentText)
                                .map(string -> string.getBytes().length)
                                .orElse(0)))
                .contentType(Optional.ofNullable(headers.getLast(Headers.Def.CONTENT_TYPE))
                        .map(ContentType::fromString)
                        .orElse(ContentType.APPLICATION_FORM_URLENCODED))
                .build();
    }

    protected String readChunkedContent(BufferedReader reader) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            int chunkSize = Integer.parseInt(line, 16);
            if (chunkSize == 0) {
                break;
            }
            char[] chunk = new char[chunkSize];
            int read = reader.read(chunk, 0, chunkSize);
            contentBuilder.append(chunk, 0, read);
            reader.readLine();
        }

        return contentBuilder.toString();
    }

    protected Attributes parseAttributes(String url) throws IOException {
        int queryStart = url.indexOf("?");
        if (queryStart == -1) {
            return Attributes.newAttributes();
        }

        String query = url.substring(queryStart + 1);
        String[] pairs = query.split("&");
        Properties properties = new Properties();
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8.name());
            String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8.name());
            properties.put(key, value);
        }
        return Attributes.fromProperties(properties);
    }

    @Override
    public HttpProtocol protocol() {
        return HttpProtocol.HTTP_1_1;
    }
}
