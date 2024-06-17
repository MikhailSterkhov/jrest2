package com.jrest.binary;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Getter
@ToString
public class HttpRequestProperties {
    private String name;
    private String method;
    private String uri;
    private Map<String, List<String>> headers;
    private Properties attributes;
    private Properties body;

    public HttpRequestProperties(String name, String method, String uri,
                                 Map<String, List<String>> headers, Properties attributes, Properties body) {
        this.name = name;
        this.method = method;
        this.uri = uri;
        this.headers = headers;
        this.attributes = attributes;
        this.body = body;
    }

    // Getters and setters
}