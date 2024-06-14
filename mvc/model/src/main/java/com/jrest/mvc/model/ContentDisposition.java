package com.jrest.mvc.model;

import lombok.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Класс ContentDisposition представляет заголовок Content-Disposition HTTP,
 * который используется для управления отображением или обработки прикрепленных файлов.
 * Позволяет создавать объекты с различными типами и атрибутами и предоставляет методы
 * для добавления и удаления атрибутов.
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ContentDisposition {

    public static final String TOKEN__FORM_DATA = "form-data";
    public static final String TOKEN__ATTACHMENT = "attachment";
    public static final String TOKEN__INLINE = "inline";

    /**
     * Создает новый объект ContentDisposition из указанного токена.
     *
     * @param token токен (например, "form-data", "attachment", "inline").
     * @return новый объект ContentDisposition.
     */
    public static ContentDisposition fromToken(String token) {
        return new ContentDisposition(token, new HashMap<>());
    }

    /**
     * Создает новый объект ContentDisposition для прикрепленного файла,
     * используя токен "form-data" и атрибуты "name" и "filename".
     *
     * @param file файл, для которого создается ContentDisposition.
     * @return новый объект ContentDisposition.
     */
    public static ContentDisposition fromFileFormData(File file) {
        return ContentDisposition.fromToken(ContentDisposition.TOKEN__FORM_DATA)
                .withAttr("name", "file")
                .withAttr("filename", file.getName());
    }

    /**
     * Создает новый объект ContentDisposition для прикрепленного файла,
     * используя токен "attachment" и атрибут "filename".
     *
     * @param file файл, для которого создается ContentDisposition.
     * @return новый объект ContentDisposition.
     */
    public static ContentDisposition fromFileAttachment(File file) {
        return ContentDisposition.fromToken(ContentDisposition.TOKEN__ATTACHMENT)
                .withAttr("filename", file.getName());
    }

    private final String token;
    private final Map<String, String> attributes;

    /**
     * Добавляет атрибут и его значение в текущий объект ContentDisposition.
     *
     * @param key   ключ атрибута.
     * @param value значение атрибута.
     * @return текущий объект ContentDisposition.
     */
    public ContentDisposition withAttr(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    /**
     * Удаляет указанный атрибут из текущего объекта ContentDisposition.
     *
     * @param key ключ атрибута для удаления.
     * @return текущий объект ContentDisposition.
     */
    public ContentDisposition removeAttr(String key) {
        attributes.remove(key);
        return this;
    }

    /**
     * Возвращает строковое представление объекта ContentDisposition,
     * включая токен и все атрибуты в виде строки.
     *
     * @return строковое представление объекта ContentDisposition.
     */
    @Override
    public String toString() {
        return String.format("%s; %s", getToken(), attributesToString());
    }

    /**
     * Преобразует все атрибуты в текущем объекте ContentDisposition в строку
     * в формате "key1="value1"; key2="value2"" и возвращает ее.
     *
     * @return строковое представление всех атрибутов текущего объекта ContentDisposition.
     */
    private String attributesToString() {
        return attributes.keySet()
                .stream()
                .map(key -> String.format("%s=\"%s\"", key, attributes.get(key)))
                .collect(Collectors.joining("; "));
    }
}
