package com.jrest.mvc.model.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@UtilityClass
public class InputStreamUtil {

    public InputStream toFileInputStream(File file) {
        try {
            return Files.newInputStream(file.toPath());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public byte[] toBytesArray(InputStream inputStream) {
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);

            return bytes;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String toString(InputStream inputStream, Charset charset) {
        return new String(toBytesArray(inputStream), charset);
    }

    public String toString(InputStream inputStream) {
        return toString(inputStream, StandardCharsets.UTF_8);
    }
}
