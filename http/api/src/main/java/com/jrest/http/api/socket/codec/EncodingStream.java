package com.jrest.http.api.socket.codec;

import com.jrest.mvc.model.*;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class EncodingStream {

    private final HttpProtocol protocol;
    private final ByteArrayOutputStream internal = new ByteArrayOutputStream();

    public void writeStatusLine(HttpRequest httpRequest) {
        writeText(String.format("%s %s %s", httpRequest.getMethod().getName(), httpRequest.getPath(), protocol));
        writeNewLine();
    }

    public void writeStatusLine(HttpResponse httpResponse) {
        writeText(String.format("%s %d %s", protocol, httpResponse.getCode().getCode(), httpResponse.getCode().getMessage()));
        writeNewLine();
    }

    public void writeHeaders(Headers headers) {
        for (Map.Entry<String, List<String>> header : headers.getMap().entrySet()) {
            writeText(String.format("%s: %s", header.getKey(), String.join(", ", header.getValue())));
            writeNewLine();
        }
        writeNewLine();
    }

    public void writeContent(Content content, Headers headers) {
        if (content != null && !content.isEmpty()) {
            String contentText = content.getText();

            if (headers.has(Headers.Def.TRANSFER_ENCODING, "chunked")) {
                writeChunkedContent(contentText);
            } else {
                writeText(contentText);
            }
        }
    }

    public void writeChunkedContent(String content) {
        int chunkSize = 1024; // Define chunk size
        int offset = 0;
        while (offset < content.length()) {
            int end = Math.min(content.length(), offset + chunkSize);
            String chunk = content.substring(offset, end);
            writeText(Integer.toHexString(chunk.length()));
            writeNewLine();
            writeText(chunk);
            writeNewLine();
            offset += chunkSize;
        }
        writeText("0");
        writeNewLine();
        writeNewLine(); // End of chunks
    }

    private void writeText(String string) {
        try {
            internal.write(string.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new HttpCodecException(e);
        }
    }

    private void writeNewLine() {
        writeText("\r\n");
    }

    public ByteArrayOutputStream toOutputStream() {
        return internal;
    }
}
