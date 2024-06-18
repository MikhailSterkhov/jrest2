package com.jrest.binary.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Getter
@ToString
@RequiredArgsConstructor
public class BinaryRequest {

    private final String name;
    private final String method;
    private final String uri;
    private final Map<String, List<String>> headers;
    private final Properties attributes;
    private final Properties body;
}