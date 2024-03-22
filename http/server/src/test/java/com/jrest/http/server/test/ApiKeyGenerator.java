package com.jrest.http.server.test;

import java.math.BigInteger;
import java.security.SecureRandom;

public class ApiKeyGenerator {
    private static final int KEY_LENGTH = 32; // Длина ключа в байтах

    public static String generateApiKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] apiKeyBytes = new byte[KEY_LENGTH];
        secureRandom.nextBytes(apiKeyBytes);
        return new BigInteger(1, apiKeyBytes).toString(16);
    }

    public static void main(String[] args) {
        String apiKey = generateApiKey();
        System.out.println("Сгенерированный API-ключ: " + apiKey);
    }
}