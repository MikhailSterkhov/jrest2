package com.jrest.binary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

@Getter
@ToString
@RequiredArgsConstructor
public class CompletedBinary {

    private final HttpClientProperties client;
    private final List<HttpRequestProperties> requests;

    public Optional<HttpRequestProperties> findRequest(String name) {
        return requests.stream()
                .filter(httpRequestProperties -> httpRequestProperties.getName().equals(name))
                .findFirst();
    }
}
