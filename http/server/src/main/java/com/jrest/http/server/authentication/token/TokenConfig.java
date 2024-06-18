package com.jrest.http.server.authentication.token;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class TokenConfig {

    private final int length;
    private final int radix;
    private final long seed;
}
