package com.jrest.http.server.resource;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.net.URI;
import java.util.Optional;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpResourcePath {

    //private static final String URI_DELIMITER = "/";

    //public static HttpResourcePath fromUri(String uri) {
    //    return new HttpResourcePath(uri, delegateToPaths(uri));
    //}

    public static HttpResourcePath fromUri(String uri) {
        return new HttpResourcePath(uri);
    }

    //private static String[] delegateToPaths(String uri) {
    //    return Optional.of(uri)
    //            .map(s -> s.startsWith(URI_DELIMITER) ? s.substring(1) : s)
    //            .map(s -> s.split(URI_DELIMITER))
    //            .orElseGet(() -> new String[]{uri});
    //}

    private final String full;
    //private final String[] paths;

    //public boolean isExpected(String otherUri) {
    //    if (full.equalsIgnoreCase(otherUri)) {
    //        return true;
    //    }

    //    int equals = 0;
    //    for (String path : paths) {
    //        if (otherUri.contains(path)) {
    //            equals++;
    //        }
    //    }

    //    return equals >= Math.max(1, paths.length - 2);
    //}

    boolean isExpected(String requestUri) {
        return requestUri.equalsIgnoreCase(full);
    }

    public URI toURI() {
        return URI.create(full);
    }
}
