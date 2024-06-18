package com.jrest.http.server.authentication.token;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Генератор аутентификационных ключей (API ключей).
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TokenGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    private final TokenConfig config;

    /**
     * Создает экземпляр генератора ключей с заданной конфигурацией.
     *
     * @param config конфигурация генератора ключей
     * @return экземпляр генератора ключей
     */
    public static TokenGenerator from(TokenConfig config) {
        return new TokenGenerator(config);
    }

    /**
     * Создает экземпляр генератора ключей с параметрами по умолчанию.
     *
     * @return экземпляр генератора ключей
     */
    public static TokenGenerator defaults() {
        return defaults(25);
    }

    /**
     * Создает экземпляр генератора ключей с параметрами по умолчанию и указанной длиной.
     *
     * @param length длина ключа
     * @return экземпляр генератора ключей
     */
    public static TokenGenerator defaults(int length) {
        return from(
                TokenConfig.builder()
                        .radix(16)
                        .length(length)
                        .build());
    }

    /**
     * Генерирует новый аутентификационный ключ на основе текущей конфигурации.
     *
     * @return сгенерированный аутентификационный ключ
     */
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
