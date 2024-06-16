package com.jrest.mvc.model.util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * Утилитарный класс для работы с InputStream.
 */
@UtilityClass
public class InputStreamUtil {

    /**
     * Преобразует объект File в InputStream.
     *
     * @param file Файл для преобразования
     * @return InputStream, представляющий содержимое файла
     * @throws RuntimeException если происходит IOException при открытии файла
     */
    public InputStream toFileInputStream(File file) {
        try {
            return Files.newInputStream(file.toPath());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Преобразует InputStream в массив байтов.
     *
     * @param inputStream InputStream для преобразования
     * @return массив байтов, содержащий данные из InputStream
     * @throws RuntimeException если происходит IOException при чтении из InputStream
     */
    public byte[] toBytesArray(InputStream inputStream) {
        try {
            return toBytesArray(inputStream, inputStream.available());
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Преобразует InputStream в массив байтов.
     *
     * @param inputStream InputStream для преобразования
     * @return массив байтов, содержащий данные из InputStream
     * @throws RuntimeException если происходит IOException при чтении из InputStream
     */
    public byte[] toBytesArray(InputStream inputStream, int length) {
        try {
            byte[] bytes = new byte[length];
            inputStream.read(bytes);
            return bytes;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    /**
     * Преобразует InputStream в строку с использованием указанной кодировки Charset.
     *
     * @param inputStream InputStream для преобразования
     * @param charset     кодировка Charset для декодирования InputStream
     * @return строка, содержащая данные из InputStream
     * @throws RuntimeException если происходит IOException при чтении из InputStream
     */
    public String toString(InputStream inputStream, Charset charset) {
        return new String(toBytesArray(inputStream), charset);
    }

    /**
     * Преобразует InputStream в строку, используя кодировку UTF-8 по умолчанию.
     *
     * @param inputStream InputStream для преобразования
     * @return строка, содержащая данные из InputStream, с использованием кодировки UTF-8
     * @throws RuntimeException если происходит IOException при чтении из InputStream
     */
    public String toString(InputStream inputStream) {
        return toString(inputStream, StandardCharsets.UTF_8);
    }
}