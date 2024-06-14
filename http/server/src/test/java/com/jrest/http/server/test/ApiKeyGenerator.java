package com.jrest.http.server.test;

import com.jrest.http.server.apikey.AuthKeyGenerator;

public class ApiKeyGenerator {

    public static void main(String[] args) {
        System.out.println("Сгенерированный API-ключ: " + AuthKeyGenerator.defaults(20).generate());
    }
}