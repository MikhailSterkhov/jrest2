package com.jrest.binary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Optional;
import java.util.Properties;

@Getter
@ToString
@RequiredArgsConstructor
public class HttpClientProperties {

    public static final class Key {
        public static final String HOST = "host";
    }

    private final Properties properties;

    public String getString(String property) {
        return properties.getProperty(property);
    }

    public Integer getInteger(String property) {
        return Optional.ofNullable(getString(property)).map(Integer::parseInt)
                .orElse(null);
    }

    public Boolean getBoolean(String property) {
        return Optional.ofNullable(getString(property)).map(Boolean::parseBoolean)
                .orElse(null);
    }
}