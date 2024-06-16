package com.jrest.mvc.model;

import lombok.*;

/**
 * Класс ContentType предоставляет константы для различных типов содержимого MIME.
 * Также позволяет создавать новые типы содержимого из строки.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentType {

    public static final ContentType APPLICATION_ATOM_XML = fromString("application/atom+xml");
    public static final ContentType APPLICATION_FORM_URLENCODED = fromString("application/x-www-form-urlencoded");
    public static final ContentType APPLICATION_JSON = fromString("application/json");
    public static final ContentType APPLICATION_OCTET_STREAM = fromString("application/octet-stream");
    public static final ContentType APPLICATION_SOAP_XML = fromString("application/soap+xml");
    public static final ContentType APPLICATION_SVG_XML = fromString("application/svg+xml");
    public static final ContentType APPLICATION_XHTML_XML = fromString("application/xhtml+xml");
    public static final ContentType APPLICATION_XML = fromString("application/xml");
    public static final ContentType IMAGE_BMP = fromString("image/bmp");
    public static final ContentType IMAGE_GIF = fromString("image/gif");
    public static final ContentType IMAGE_JPEG = fromString("image/jpeg");
    public static final ContentType IMAGE_PNG = fromString("image/png");
    public static final ContentType IMAGE_SVG = fromString("image/svg+xml");
    public static final ContentType IMAGE_TIFF = fromString("image/tiff");
    public static final ContentType IMAGE_WEBP = fromString("image/webp");
    public static final ContentType MULTIPART_FORM_DATA = fromString("multipart/form-data");
    public static final ContentType TEXT_HTML = fromString("text/html");
    public static final ContentType TEXT_PLAIN = fromString("text/plain");
    public static final ContentType TEXT_XML = fromString("text/xml");
    public static final ContentType WILDCARD = fromString("*/*");

    public static final ContentType DEFAULT_TEXT = TEXT_PLAIN;
    public static final ContentType DEFAULT_BINARY = APPLICATION_OCTET_STREAM;

    /**
     * Создает новый объект ContentType из переданной строки MIME-типа.
     *
     * @param value строка MIME-типа.
     * @return новый объект ContentType.
     */
    public static ContentType fromString(String value) {
        return new ContentType(value);
    }

    private final String mime;

    @Override
    public String toString() {
        return mime;
    }
}
