package com.jrest.mvc.model;

import lombok.*;

import java.util.Optional;
import java.util.Properties;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Attributes {

    public static Attributes newAttributes() {
        return new Attributes();
    }

    private final Properties properties = new Properties();

    public Optional<String> getString(String name) {
        return Optional.ofNullable(properties.getProperty(name));
    }

    public Optional<Boolean> getBoolean(String name) {
        return getString(name).map(Boolean::parseBoolean);
    }

    public Optional<Integer> getInteger(String name) {
        return getString(name).map(Integer::parseInt);
    }

    public Optional<Long> getLong(String name) {
        return getString(name).map(Long::parseLong);
    }

    public boolean contains(String name) {
        return properties.getProperty(name) != null;
    }

    public Attributes with(String name, Object value) {
        // todo: encoding to url from value#toString()
        return this;
    }
}
