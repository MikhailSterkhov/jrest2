package com.jrest.http.api.socket;

import com.jrest.mvc.model.HttpRequest;
import com.jrest.mvc.model.HttpResponse;

import java.util.Arrays;

public class HttpSocketOutput {

    private byte[] buffer;

    public HttpSocketOutput write(int i) {
        if (i > Byte.MAX_VALUE) {
            throw new IllegalArgumentException(i + " > " + Byte.MAX_VALUE);
        }
        if (i < Byte.MIN_VALUE) {
            throw new IllegalArgumentException(i + " < " + Byte.MIN_VALUE);
        }
        return write((byte) i);
    }

    public HttpSocketOutput write(byte b) {
        return write(new byte[]{b});
    }

    public HttpSocketOutput write(byte[] array) {
        this.buffer = array;
        return this;
    }

    public HttpSocketOutput append(int i) {
        if (i > Byte.MAX_VALUE) {
            throw new IllegalArgumentException(i + " > " + Byte.MAX_VALUE);
        }
        if (i < Byte.MIN_VALUE) {
            throw new IllegalArgumentException(i + " < " + Byte.MIN_VALUE);
        }
        return append((byte) i);
    }

    public HttpSocketOutput append(byte b) {
        return append(new byte[]{b});
    }

    public HttpSocketOutput append(byte[] array) {
        if (buffer == null) {
            write(new byte[0]);
        }

        byte[] full = Arrays.copyOf(buffer, buffer.length + array.length);
        System.arraycopy(full, 0, array, buffer.length, full.length);

        return write(full);
    }

    public HttpSocketOutput write(HttpRequest httpRequest) {
        // todo
        return this;
    }

    public HttpSocketOutput write(HttpResponse httpResponse) {
        // todo
        return this;
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(buffer, buffer.length);
    }
}
