package com.jrest.mvc.model;

import com.jrest.mvc.model.util.ContentUtil;
import lombok.*;

import java.io.File;

/**
 * Класс Content представляет контент для передачи в HTTP запросах или ответах.
 * Позволяет создавать различные типы контента из различных источников данных,
 * таких как массив байтов, текст, объекты, файлы и мультипарт контент.
 */
@Getter
@ToString
@EqualsAndHashCode
@Builder(access = AccessLevel.PRIVATE)
public class Content {

    /**
     * Создает объект Content из указанного контента и типа содержимого.
     *
     * @param contentType тип содержимого (например, "application/json", "text/plain").
     * @param array       массив байтов, представляющий контент.
     * @return новый объект Content.
     */
    public static Content from(ContentType contentType, byte[] array) {
        return Content.builder()
                .hyperText(ContentUtil.toHyperText(array))
                .contentType(contentType)
                .contentLength(array.length)
                .build();
    }

    /**
     * Создает объект Content из указанного текста с типом по умолчанию "text/plain".
     *
     * @param text текстовое содержимое.
     * @return новый объект Content.
     */
    public static Content fromText(String text) {
        return Content.builder()
                .hyperText(text)
                .contentType(ContentType.DEFAULT_TEXT)
                .contentLength(ContentUtil.fromString(text).length)
                .build();
    }

    /**
     * Создает объект Content из указанного объекта, сериализуя его в JSON и используя тип "application/json".
     *
     * @param entity объект, который будет сериализован в JSON.
     * @return новый объект Content.
     */
    public static Content fromEntity(Object entity) {
        byte[] bytes = ContentUtil.fromJsonEntity(entity);
        return Content.builder()
                .hyperText(ContentUtil.toHyperText(bytes))
                .contentType(ContentType.APPLICATION_JSON)
                .contentLength(bytes.length)
                .build();
    }

    /**
     * Создает объект Content из прикрепленного файла с типом "multipart/form-data".
     *
     * @param file файл, который будет прикреплен к запросу.
     * @return новый объект Content.
     */
    public static Content fromAttachment(File file) {
        return from(ContentType.MULTIPART_FORM_DATA, ContentUtil.fromFileAsAttachment(file));
    }

    /**
     * Создает объект Content из содержимого файла с типом "multipart/form-data".
     *
     * @param file файл, содержимое которого будет передано в запросе.
     * @return новый объект Content.
     */
    public static Content fromFile(File file) {
        return from(ContentType.MULTIPART_FORM_DATA, ContentUtil.fromFile(file));
    }

    /**
     * Создает объект Content из содержимого файла с типом "multipart/form-data".
     * Использует содержимое файла в виде строки.
     *
     * @param file файл, содержимое которого будет передано в запросе.
     * @return новый объект Content.
     */
    public static Content fromFileContent(File file) {
        return from(ContentType.MULTIPART_FORM_DATA, ContentUtil.fromFileContent(file));
    }

    /**
     * Создает объект Content из мультипарт содержимого с указанным типом и атрибутами.
     *
     * @param partsContentType тип содержимого частей мультипарта (например, "text/plain").
     * @param contentDisposition заголовок Content-Disposition для частей мультипарта.
     * @param parts части мультипарта, которые будут включены в контент.
     * @return новый объект Content.
     */
    public static Content fromMultipart(ContentType partsContentType, ContentDisposition contentDisposition, Content... parts) {
        return from(ContentType.MULTIPART_FORM_DATA, ContentUtil.fromMultipart(partsContentType, contentDisposition,
                ContentUtil.toMultipart(parts)));
    }

    /**
     * Создает объект Content из текстового мультипарт содержимого с указанным заголовком Content-Disposition.
     *
     * @param contentDisposition заголовок Content-Disposition для частей мультипарта.
     * @param parts части мультипарта, которые будут включены в контент.
     * @return новый объект Content.
     */
    public static Content fromTextMultipart(ContentDisposition contentDisposition, Content... parts) {
        return from(ContentType.MULTIPART_FORM_DATA, ContentUtil.fromMultipart(ContentType.DEFAULT_TEXT, contentDisposition,
                ContentUtil.toMultipart(parts)));
    }

    /**
     * Создает объект Content из текстового мультипарт содержимого без заголовка Content-Disposition.
     *
     * @param parts части мультипарта, которые будут включены в контент.
     * @return новый объект Content.
     */
    public static Content fromTextMultipart(Content... parts) {
        return from(ContentType.MULTIPART_FORM_DATA, ContentUtil.fromMultipart(ContentType.DEFAULT_TEXT, null,
                ContentUtil.toMultipart(parts)));
    }

    /**
     * Создает объект Content из мультипарт содержимого с указанным типом частей.
     * Использует мультипарт содержимое без заголовка Content-Disposition.
     *
     * @param partsContentType тип содержимого частей мультипарта (например, "text/plain").
     * @param parts части мультипарта, которые будут включены в контент.
     * @return новый объект Content.
     */
    public static Content fromMultipart(ContentType partsContentType, Content... parts) {
        return from(ContentType.MULTIPART_FORM_DATA, ContentUtil.fromMultipart(partsContentType, null,
                ContentUtil.toMultipart(parts)));
    }

    private final int contentLength;
    private final ContentType contentType;
    private final String hyperText;

    /**
     * Преобразует текущий объект Content в экземпляр заданного класса с использованием
     * метода десериализации JSON из строки.
     *
     * @param entityClass класс объекта, в который требуется преобразовать текущий контент.
     * @param <T>         тип объекта.
     * @return экземпляр объекта, десериализованного из текущего контента.
     */
    public <T> T toEntity(Class<T> entityClass) {
        return ContentUtil.toJsonEntity(getHyperText(), entityClass);
    }
}
