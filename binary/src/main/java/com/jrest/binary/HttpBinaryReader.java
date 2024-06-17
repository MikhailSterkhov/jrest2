package com.jrest.binary;

import com.jrest.binary.data.CompletedBinary;
import com.jrest.binary.data.HttpClientProperties;
import com.jrest.binary.data.HttpRequestProperties;
import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Утилитарный класс для чтения бинарных HTTP данных из различных источников.
 */
@UtilityClass
public final class HttpBinaryReader {

    /**
     * Читает бинарные данные из {@link Reader}.
     *
     * @param reader источник данных
     * @return {@link CompletedBinary} содержащий свойства клиента и запросов
     */
    public CompletedBinary read(Reader reader) {
        HttpBinaryParser httpBinaryParser = new HttpBinaryParser();
        BufferedReader bufferedReader = new BufferedReader(reader);

        HttpClientProperties httpClientProperties = httpBinaryParser.parseClientConfig(bufferedReader);
        List<HttpRequestProperties> httpRequestsProperties = httpBinaryParser.parseRequestConfigs(bufferedReader);

        return new CompletedBinary(httpClientProperties, httpRequestsProperties);
    }

    /**
     * Читает бинарные данные из {@link InputStream}.
     *
     * @param inputStream поток данных
     * @return {@link CompletedBinary} содержащий свойства клиента и запросов
     */
    public CompletedBinary read(InputStream inputStream) {
        return read(new InputStreamReader(inputStream));
    }

    /**
     * Читает бинарные данные из {@link File}.
     *
     * @param file файл с данными
     * @return {@link CompletedBinary} содержащий свойства клиента и запросов
     */
    public CompletedBinary read(File file) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            return read(inputStream);
        }
    }

    /**
     * Читает бинарные данные из {@link Path}.
     *
     * @param path путь к файлу с данными
     * @return {@link CompletedBinary} содержащий свойства клиента и запросов
     */
    public CompletedBinary read(Path path) throws IOException {
        try (InputStream inputStream = Files.newInputStream(path)) {
            return read(inputStream);
        }
    }
}
