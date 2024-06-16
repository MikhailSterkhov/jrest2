package com.jrest.http.server;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * Класс для хранения информации о конфигурации SSL.
 * Используется для настройки HTTPS соединений.
 */
@Getter
@Builder
@ToString
public class SslContent {

    /**
     * Путь к хранилищу ключей (keystore).
     */
    private final String keystorePath;

    /**
     * Пароль для хранилища ключей.
     */
    private final String keystorePassword;

    /**
     * Пароль для ключа в хранилище.
     */
    private final String keyPassword;
}
