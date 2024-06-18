package com.jrest.test.http.server;

import com.jrest.http.server.authentication.token.TokenGenerator;

public class ApiKeyGenerator {

    public static void main(String[] args) {
        System.out.println("Сгенерированный API-ключ: " + TokenGenerator.defaults(30).generate());
    }
}