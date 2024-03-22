package com.jrest.mvc.model;

import lombok.*;

import java.util.*;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Headers {

    public static final class Defaults {

        public static final String AUTHORIZATION = "Authorization";
        public static final String USER_AGENT = "User-Agent";
    }

    public static Headers newHeaders() {
        return new Headers(new HashMap<>());
    }

    public static Headers fromMap(Map<String, List<String>> map) {
        return new Headers(map);
    }

    private final Map<String, List<String>> headers;

    public Headers add(String header, String value) {
        List<String> strings = headers.get(header);
        if (strings == null) {
            strings = new ArrayList<>();
        }

        strings.add(value);
        return this;
    }

    public Headers set(String header, String value) {
        if (value == null) {
            return removeFull(header);
        }
        headers.put(header, new ArrayList<>(Collections.singletonList(value)));
        return this;
    }

    public Headers remove(String header, String value) {
        List<String> values = headers.get(header);
        if (values != null) {
            values.remove(value);
        }
        return this;
    }

    public Headers removeFull(String header) {
        headers.remove(header);
        return this;
    }

    public Headers clear() {
        headers.clear();
        return this;
    }

    public List<String> get(String header) {
        return headers.getOrDefault(header, new ArrayList<>());
    }

    public String getFirst(String header) {
        Iterator<String> valuesIterator = get(header).iterator();
        return valuesIterator.hasNext() ? valuesIterator.next() : null;
    }
}
