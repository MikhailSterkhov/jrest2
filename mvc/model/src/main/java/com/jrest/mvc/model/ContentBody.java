package com.jrest.mvc.model;

import com.jrest.mvc.model.util.BodyBytesUtil;
import lombok.*;

import java.io.File;

@Getter
@ToString
@EqualsAndHashCode
@Builder(access = AccessLevel.PRIVATE)
public class ContentBody {

    public static ContentBody fromBytes(ContentType contentType, byte[] array) {
        return ContentBody.builder()
                .hyperText(BodyBytesUtil.toHyperText(array))
                .contentType(contentType)
                .contentLength(array.length)
                .build();
    }

    public static ContentBody fromText(String text) {
        return ContentBody.builder()
                .hyperText(text)
                .contentType(ContentType.DEFAULT_TEXT)
                .contentLength(BodyBytesUtil.fromString(text).length)
                .build();
    }

    public static ContentBody fromEntity(Object entity) {
        byte[] bytes = BodyBytesUtil.fromJsonEntity(entity);
        return ContentBody.builder()
                .hyperText(BodyBytesUtil.toHyperText(bytes))
                .contentType(ContentType.APPLICATION_JSON)
                .contentLength(bytes.length)
                .build();
    }

    public static ContentBody fromAttachment(File file) {
        return fromBytes(ContentType.MULTIPART_FORM_DATA, BodyBytesUtil.fromFileAsAttachment(file));
    }

    public static ContentBody fromFile(File file) {
        return fromBytes(ContentType.MULTIPART_FORM_DATA, BodyBytesUtil.fromFile(file));
    }

    public static ContentBody fromFileContent(File file) {
        return fromBytes(ContentType.MULTIPART_FORM_DATA, BodyBytesUtil.fromFileContent(file));
    }

    public static ContentBody fromMultipart(ContentType partsContentType, ContentDisposition contentDisposition, ContentBody... parts) {
        return fromBytes(ContentType.MULTIPART_FORM_DATA, BodyBytesUtil.fromMultipart(partsContentType, contentDisposition,
                BodyBytesUtil.toMultipart(parts)));
    }

    public static ContentBody fromTextMultipart(ContentDisposition contentDisposition, ContentBody... parts) {
        return fromBytes(ContentType.MULTIPART_FORM_DATA, BodyBytesUtil.fromMultipart(ContentType.DEFAULT_TEXT, contentDisposition,
                BodyBytesUtil.toMultipart(parts)));
    }

    public static ContentBody fromTextMultipart(ContentBody... parts) {
        return fromBytes(ContentType.MULTIPART_FORM_DATA, BodyBytesUtil.fromMultipart(ContentType.DEFAULT_TEXT, null,
                BodyBytesUtil.toMultipart(parts)));
    }

    public static ContentBody fromMultipart(ContentType partsContentType, ContentBody... parts) {
        return fromBytes(ContentType.MULTIPART_FORM_DATA, BodyBytesUtil.fromMultipart(partsContentType, null,
                BodyBytesUtil.toMultipart(parts)));
    }

    private final int contentLength;

    private final ContentType contentType;
    private final String hyperText;

    public <T> T toEntity(Class<T> entityClass) {
        return BodyBytesUtil.toJsonEntity(getHyperText(), entityClass);
    }
}
