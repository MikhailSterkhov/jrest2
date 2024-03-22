package com.jrest.mvc.model;

import lombok.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentDisposition {

    public static final String TOKEN__FORM_DATA = "form-data";
    public static final String TOKEN__ATTACHMENT = "attachment";
    public static final String TOKEN__INLINE = "inline";

    public static ContentDisposition fromToken(String token) {
        return new ContentDisposition(token, new HashMap<>());
    }

    public static ContentDisposition fromFileFormData(File file) {
        return ContentDisposition.fromToken(ContentDisposition.TOKEN__FORM_DATA)
                .withAttr("name", "file")
                .withAttr("filename", file.getName());
    }

    public static ContentDisposition fromFileAttachment(File file) {
        return ContentDisposition.fromToken(ContentDisposition.TOKEN__ATTACHMENT)
                .withAttr("filename", file.getName());
    }

    private final String token;
    private final Map<String, String> attributes;

    public ContentDisposition withAttr(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    public ContentDisposition removeAttr(String key) {
        attributes.remove(key);
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s; %s", getToken(), attributesToString());
    }

    private String attributesToString() {
        return attributes.keySet()
                .stream()
                .map(key -> String.format("%s=\"%s\"", key, attributes.get(key)))
                .collect(Collectors.joining("; "));
    }
}
