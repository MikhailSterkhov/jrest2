package com.jrest.http.server.apikey;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Генератор аутентификационных ключей (API ключей).
 */
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthKeyGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    private final AuthKeyConfig config;

    /**
     * Создает экземпляр генератора ключей с заданной конфигурацией.
     *
     * @param config конфигурация генератора ключей
     * @return экземпляр генератора ключей
     */
    public static AuthKeyGenerator from(AuthKeyConfig config) {
        return new AuthKeyGenerator(config);
    }

    /**
     * Создает экземпляр генератора ключей с параметрами по умолчанию.
     *
     * @return экземпляр генератора ключей
     */
    public static AuthKeyGenerator defaults() {
        return defaults(25);
    }

    /**
     * Создает экземпляр генератора ключей с параметрами по умолчанию и указанной длиной.
     *
     * @param length длина ключа
     * @return экземпляр генератора ключей
     */
    public static AuthKeyGenerator defaults(int length) {
        return from(
                AuthKeyConfig.builder()
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
