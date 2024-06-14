package com.jrest.http.api.socket;

import com.jrest.mvc.model.util.InputStreamUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SocketChannel;

public class HttpSocket {

    private final SocketChannel channel;

    HttpSocket(SocketChannel channel) {
        this.channel = channel;
    }

    public synchronized HttpSocketInput read() {
        try {
            InputStream inputStream = channel.socket().getInputStream();
            if (inputStream.available() == 0) {
                return HttpSocketInput.empty();
            }
            byte[] buf = InputStreamUtil.toBytesArray(inputStream);
            return HttpSocketInput.fromStream(buf);
        }
        catch (IOException exception) {
            throw new HttpSocketException("read", exception);
        }
    }

    public synchronized void write(HttpSocketOutput output) {
        try {
            OutputStream outputStream = channel.socket().getOutputStream();
            outputStream.write(output.toByteArray());
        }
        catch (IOException exception) {
            throw new HttpSocketException("write", exception);
        }
    }

    public synchronized void writeAndFlush(HttpSocketOutput output) {
        write(output);
        try {
            OutputStream outputStream = channel.socket().getOutputStream();
            outputStream.flush();
        }
        catch (IOException exception) {
            throw new HttpSocketException("writeAndFlush", exception);
        }
    }
}
