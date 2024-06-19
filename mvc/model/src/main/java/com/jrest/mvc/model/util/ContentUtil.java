package com.jrest.mvc.model.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jrest.mvc.model.Content;
import com.jrest.mvc.model.ContentDisposition;
import com.jrest.mvc.model.ContentType;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Утилитарный класс для работы с содержимым (Content) и мультипартными данными.
 */
@UtilityClass
public class ContentUtil {

    private static final Gson GSON = new GsonBuilder().setLenient().create();

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    private static final String MULTIPART_BEGIN = "--===\r\n";
    private static final String MULTIPART_SPLITERATOR = "\r\n";
    private static final String MULTIPART_END = "--===--\r\n";

    /**
     * Преобразует массив байтов в строку UTF-8.
     *
     * @param bytes массив байтов для преобразования
     * @return строка UTF-8, созданная из массива байтов
     */
    public String toText(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Преобразует строку в массив байтов UTF-8.
     *
     * @param string строка для преобразования в массив байтов
     * @return массив байтов UTF-8, созданный из строки
     */
    public byte[] fromString(String string) {
        return string.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Преобразует объект в массив байтов, содержащий его JSON-представление.
     *
     * @param object объект для преобразования в JSON
     * @return массив байтов, содержащий JSON-представление объекта
     */
    public byte[] fromJsonEntity(Object object) {
        return fromString(GSON.toJson(object));
    }

    /**
     * Преобразует объект в массив байтов, содержащий его XML-представление.
     *
     * @param object объект для преобразования в JSON
     * @return массив байтов, содержащий JSON-представление объекта
     */
    @SneakyThrows({JsonProcessingException.class})
    public byte[] fromXmlEntity(Object object) {
        return fromString(XML_MAPPER.writeValueAsString(object));
    }

    /**
     * Генерирует мультипартное содержимое из файла с использованием формата вложения.
     *
     * @param file файл для вложения
     * @return мультипартное содержимое в виде массива байтов
     */
    public byte[] fromFileAsAttachment(File file) {
        return fromMultipart(ContentType.TEXT_PLAIN, ContentDisposition.fromFileAttachment(file), fromFileContent(file));
    }

    /**
     * Генерирует мультипартное содержимое из файла с использованием формата формы данных.
     *
     * @param file файл для вложения
     * @return мультипартное содержимое в виде массива байтов
     */
    public byte[] fromFile(File file) {
        return fromMultipart(ContentType.TEXT_PLAIN, ContentDisposition.fromFileFormData(file), fromFileContent(file));
    }

    /**
     * Преобразует содержимое файла в массив байтов.
     *
     * @param file файл для преобразования
     * @return массив байтов, содержащий содержимое файла
     */
    public byte[] fromFileContent(File file) {
        return fromString(InputStreamUtil.toString(InputStreamUtil.toFileInputStream(file)));
    }

    /**
     * Генерирует мультипартное содержимое из заданных частей с указанием типа содержимого и параметров.
     *
     * @param contentType         тип содержимого (например, текст или бинарные данные)
     * @param contentDisposition параметры вложения (например, имя файла)
     * @param parts               части содержимого в виде массива байтов
     * @return мультипартное содержимое в виде массива байтов
     */
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
            content.append(toText(bytesPart))
                    .append(MULTIPART_SPLITERATOR);
        }

        content.append(MULTIPART_END);
        return fromString(content.toString());
    }

    /**
     * Преобразует массив объектов Content в массив байтов для мультипартного содержимого.
     *
     * @param bodies массив объектов Content для преобразования
     * @return массив байтов, содержащий каждое содержимое Content в виде массива байтов
     */
    public byte[][] toMultipart(Content[] bodies) {
        byte[][] result = new byte[bodies.length][];

        for (int i = 0; i < bodies.length; i++) {
            result[i] = fromString(bodies[i].getText());
        }

        return result;
    }

    /**
     * Преобразует строковое представление JSON в объект заданного класса.
     *
     * @param hyperText   строковое представление JSON для преобразования
     * @param entityClass класс объекта, в который будет преобразован JSON
     * @param <T>         тип объекта
     * @return объект заданного класса, созданный из JSON-представления
     */
    public <T> T toJsonEntity(String hyperText, Class<T> entityClass) {
        return GSON.fromJson(hyperText, entityClass);
    }

    /**
     * Преобразует строковое представление XML в объект заданного класса.
     *
     * @param hyperText   строковое представление XML для преобразования
     * @param entityClass класс объекта, в который будет преобразован JSON
     * @param <T>         тип объекта
     * @return объект заданного класса, созданный из XM:-представления
     */
    @SneakyThrows({IOException.class})
    public <T> T toXmlEntity(String hyperText, Class<T> entityClass) {
        return XML_MAPPER.readValue(hyperText, entityClass);
    }
}
