package com.jrest.mvc.model.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jrest.mvc.model.ContentBody;
import com.jrest.mvc.model.ContentDisposition;
import com.jrest.mvc.model.ContentType;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class BodyBytesUtil {

    private static final Gson GSON = new GsonBuilder().setLenient().create();

    private static final String MULTIPART_BEGIN = "--===\r\n";
    private static final String MULTIPART_SPLITERATOR = "\r\n";
    private static final String MULTIPART_END = "--===--\r\n";

    public String toHyperText(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public byte[] fromString(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    public byte[] fromJsonEntity(Object object) {
        return fromString(GSON.toJson(object));
    }

    public byte[] fromFileAsAttachment(File file) {
        return fromMultipart(ContentType.TEXT_PLAIN, ContentDisposition.fromFileAttachment(file), fromFileContent(file));
    }

    public byte[] fromFile(File file) {
        return fromMultipart(ContentType.TEXT_PLAIN, ContentDisposition.fromFileFormData(file), fromFileContent(file));
    }

    public byte[] fromFileContent(File file) {
        return fromString(InputStreamUtil.toString(InputStreamUtil.toFileInputStream(file)));
    }

    public byte[] fromMultipart(ContentType contentType, ContentDisposition contentDisposition, byte[]... parts) {
        StringBuilder content = new StringBuilder();
        content.append(MULTIPART_BEGIN);

        if (contentDisposition != null) {
            content.append("Content-Disposition: ").append(contentDisposition)
                    .append(MULTIPART_SPLITERATOR);
        }
        if (contentType != null) {
            content.append("Content-Type: ").append(contentType.getMime())
                    .append(MULTIPART_SPLITERATOR);
        }

        content.append(MULTIPART_SPLITERATOR);

        for (byte[] bytesPart : parts) {
            content.append(toHyperText(bytesPart))
                    .append(MULTIPART_SPLITERATOR);
        }

        content.append(MULTIPART_END);
        return fromString(content.toString());
    }

    public byte[][] toMultipart(ContentBody[] bodies) {
        byte[][] result = new byte[bodies.length][];

        for (int i = 0; i < bodies.length; i++) {
            result[i] = fromString(bodies[i].getHyperText());
        }

        return result;
    }

    public <T> T toJsonEntity(String hyperText, Class<T> entityClass) {
        return GSON.fromJson(hyperText, entityClass);
    }
}
