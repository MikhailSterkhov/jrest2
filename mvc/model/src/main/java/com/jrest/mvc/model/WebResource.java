package com.jrest.mvc.model;

import com.jrest.mvc.model.util.BodyBytesUtil;
import com.jrest.mvc.model.util.InputStreamUtil;
import lombok.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class WebResource {

    public static WebResource fromStream(InputStream inputStream) {
        return new WebResource(InputStreamUtil.toBytesArray(inputStream));
    }

    public static WebResource fromClassLoader(String resourceName) {
        return fromStream(WebResource.class.getResourceAsStream(resourceName));
    }

    public static WebResource fromFile(File file) {
        return fromStream(InputStreamUtil.toFileInputStream(file));
    }

    public static WebResource fromText(String text) {
        return fromStream(new ByteArrayInputStream(BodyBytesUtil.fromString(text)));
    }

    private final byte[] bytesArray;
}
