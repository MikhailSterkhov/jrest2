package com.jrest.mvc.model.util;

import com.jrest.mvc.model.HttpRestException;
import lombok.experimental.UtilityClass;

import java.io.ByteArrayOutputStream;
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
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        try {
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new HttpRestException(e);
        }

        return outputStream.toByteArray();
    }

    /**
     * Преобразует InputStream в массив байтов.
     *
     * @param inputStream InputStream для преобразования
     * @return массив байтов, содержащий данные из InputStream
     * @throws RuntimeException если происходит IOException при чтении из InputStream
     */
    public byte[] toBytesArray(InputStream inputStream, int length) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[length];

        try {
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new HttpRestException(e);
        }

        return outputStream.toByteArray();
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