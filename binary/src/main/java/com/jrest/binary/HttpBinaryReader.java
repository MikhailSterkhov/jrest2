package com.jrest.binary;

import lombok.experimental.UtilityClass;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@UtilityClass
public final class HttpBinaryReader {

    public CompletedBinary read(Reader reader) {
        HttpBinaryParser httpBinaryParser = new HttpBinaryParser();
        BufferedReader bufferedReader = new BufferedReader(reader);

        HttpClientProperties httpClientProperties = httpBinaryParser.parseClientConfig(bufferedReader);
        List<HttpRequestProperties> httpRequestsProperties = httpBinaryParser.parseRequestConfigs(bufferedReader);

        return new CompletedBinary(httpClientProperties, httpRequestsProperties);
    }

    public CompletedBinary read(InputStream inputStream) {
        return read(new InputStreamReader(inputStream));
    }

    public CompletedBinary read(File file) throws IOException {
        try (InputStream inputStream = new FileInputStream(file)) {
            return read(inputStream);
        }
    }

    public CompletedBinary read(Path path) throws IOException {
        try (InputStream inputStream = Files.newInputStream(path)) {
            return read(inputStream);
        }
    }
}
