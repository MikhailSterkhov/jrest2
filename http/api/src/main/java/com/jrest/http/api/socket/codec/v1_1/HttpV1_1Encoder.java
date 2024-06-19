package com.jrest.http.api.socket.codec.v1_1;

import com.jrest.http.api.socket.codec.HttpCodecException;
import com.jrest.http.api.socket.codec.HttpEncoder;
import com.jrest.mvc.model.*;

import java.io.*;
import java.util.List;
import java.util.Map;

public class HttpV1_1Encoder implements HttpEncoder {

    @Override
    public ByteArrayOutputStream encode0(HttpRequest httpRequest) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            // Write request line
            writer.write(httpRequest.getMethod().getName() + " " + httpRequest.getPath() + " " + protocol());
            writer.newLine();

            // Write headers
            for (Map.Entry<String, List<String>> header : httpRequest.getHeaders().getMap().entrySet()) {
                for (String value : header.getValue()) {
                    writer.write(header.getKey() + ": " + value);
                    writer.newLine();
                }
            }

            writer.newLine();

            // Write body
            Content content = httpRequest.getContent();
            if (content != null && !content.isEmpty()) {
                String contentText = content.getText();

                if (httpRequest.getHeaders().has(Headers.Def.TRANSFER_ENCODING, "chunked")) {
                    writeChunkedContent(writer, contentText);
                } else {
                    writer.write(contentText);
                }
            }

            writer.flush();
            return outputStream;
        } catch (Throwable ex) {
            new HttpCodecException("failed request encode", ex).printStackTrace();
            return null;
        }
    }

    @Override
    public ByteArrayOutputStream encode1(HttpResponse httpResponse) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            // Write status line
            writer.write(protocol() + " " + httpResponse.getCode().getCode() + " " + httpResponse.getCode().getMessage());
            writer.newLine();

            // Write headers
            for (Map.Entry<String, List<String>> header : httpResponse.getHeaders().getMap().entrySet()) {
                for (String value : header.getValue()) {
                    writer.write(header.getKey() + ": " + value);
                    writer.newLine();
                }
            }

            writer.newLine();

            // Write body
            Content content = httpResponse.getContent();
            if (content != null && !content.isEmpty()) {
                String contentText = content.getText();

                if (httpResponse.getHeaders().has(Headers.Def.TRANSFER_ENCODING, "chunked")) {
                    writeChunkedContent(writer, contentText);
                } else {
                    writer.write(contentText);
                }
            }

            writer.flush();
            return outputStream;
        } catch (Throwable ex) {
            new HttpCodecException("failed response encode", ex).printStackTrace();
            return null;
        }
    }

    protected void writeChunkedContent(BufferedWriter writer, String content) throws IOException {
        int chunkSize = 1024; // Define chunk size
        int offset = 0;
        while (offset < content.length()) {
            int end = Math.min(content.length(), offset + chunkSize);
            String chunk = content.substring(offset, end);
            writer.write(Integer.toHexString(chunk.length()));
            writer.newLine();
            writer.write(chunk);
            writer.newLine();
            offset += chunkSize;
        }
        writer.write("0");
        writer.newLine();
        writer.newLine(); // End of chunks
    }

    @Override
    public HttpProtocol protocol() {
        return HttpProtocol.HTTP_1_1;
    }
}
