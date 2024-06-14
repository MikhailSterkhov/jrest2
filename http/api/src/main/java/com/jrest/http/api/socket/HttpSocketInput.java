package com.jrest.http.api.socket;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.nio.charset.Charset;
import java.util.Scanner;

@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class HttpSocketInput {

    static HttpSocketInput empty() {
        return new HttpSocketInput(new byte[0]);
    }

    static HttpSocketInput fromStream(byte[] buf) {
        return new HttpSocketInput(buf);
    }

    private final byte[] buf;

    private String toString(Charset charset) {
        return new String(buf, charset);
    }

    public HttpRequest toHttpRequest(Charset charset) {
        BufferLinesReader reader = new BufferLinesReader(charset);
        return HttpRequest.builder()
                .url(null)
                .uri(null)
                .method(null)
                .attributes(null)
                .headers(null)
                .content(null)
                .build();
    }

    public HttpResponse toHttpResponse(Charset charset) {
        BufferLinesReader reader = new BufferLinesReader(charset);
        return HttpResponse.builder()
                .code(null)
                .headers(null)
                .content(null)
                .build();
    }

    private

    @RequiredArgsConstructor
    class BufferLinesReader {

        private final Charset charset;

        public String readLine(int index) {
            if (index < 0) {
                throw new IllegalArgumentException(index + " < 0");
            }

            String string = HttpSocketInput.this.toString(charset);

            Scanner scanner = new Scanner(string);
            int count = 0;

            String line = null;
            while (count <= index) {
                line = scanner.nextLine();
                if (line == null) {
                    throw new IllegalArgumentException(index  + " > " + count);
                }
                count++;
            }

            return line;
        }
    }
}
