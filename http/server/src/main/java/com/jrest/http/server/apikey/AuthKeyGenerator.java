package com.jrest.http.server.apikey;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.security.SecureRandom;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthKeyGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static AuthKeyGenerator from(AuthKeyConfig config) {
        return new AuthKeyGenerator(config);
    }

    public static AuthKeyGenerator defaults() {
        return defaults(25);
    }

    public static AuthKeyGenerator defaults(int length) {
        return from(
                AuthKeyConfig.builder()
                        .radix(16)
                        .length(length)
                        .build());
    }

    private final AuthKeyConfig config;

    public synchronized String generate() {
        long seed = config.getSeed();
        if (seed != 0) {
            secureRandom.setSeed(seed);
        }

        byte[] apiKeyBytes = new byte[config.getLength()];
        secureRandom.nextBytes(apiKeyBytes);

        BigInteger bigInteger = new BigInteger(1, apiKeyBytes);
        return bigInteger.toString(config.getRadix());
    }
}
