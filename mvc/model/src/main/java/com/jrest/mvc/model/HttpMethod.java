package com.jrest.mvc.model;

import lombok.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpMethod {

    public static final HttpMethod GET = fromName("GET");
    public static final HttpMethod POST = fromName("POST");
    public static final HttpMethod DELETE = fromName("DELETE");
    public static final HttpMethod PUT = fromName("PUT");
    public static final HttpMethod TRACE = fromName("TRACE");
    public static final HttpMethod CONNECT = fromName("CONNECT");
    public static final HttpMethod HEAD = fromName("HEAD");
    public static final HttpMethod OPTIONS = fromName("OPTIONS");
    public static final HttpMethod PATCH = fromName("PATCH");

    private static final Set<HttpMethod> BASED = new HashSet<>(Arrays.asList(GET, POST, DELETE, PUT, TRACE, CONNECT, HEAD, OPTIONS, PATCH));
    private static final Set<HttpMethod> IDEMPOTENT = new HashSet<>(Arrays.asList(GET, HEAD, PUT, DELETE, OPTIONS, TRACE));
    private static final Set<HttpMethod> NEEDS_BODY = new HashSet<>(Arrays.asList(POST, PUT, CONNECT, PATCH));
    private static final Set<HttpMethod> CACHEABLE = new HashSet<>(Arrays.asList(GET, HEAD, POST, PATCH));

    public static HttpMethod fromName(String name) {
        return new HttpMethod(name.toUpperCase());
    }

    private final String name;

    public boolean isHttpBase() {
        return BASED.contains(this);
    }

    public boolean isIdempotent() {
        return IDEMPOTENT.contains(this);
    }

    public boolean needsBody() {
        return NEEDS_BODY.contains(this);
    }

    public boolean isCacheable() {
        return CACHEABLE.contains(this);
    }

    public boolean needsResponse() {
        return !Objects.equals(this, HEAD);
    }
}
