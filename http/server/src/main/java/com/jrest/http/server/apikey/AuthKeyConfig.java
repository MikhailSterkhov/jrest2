package com.jrest.http.server.apikey;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class AuthKeyConfig {

    private final int length;
    private final int radix;
    private final long seed;
}
