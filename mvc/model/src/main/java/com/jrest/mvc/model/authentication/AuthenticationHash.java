package com.jrest.mvc.model.authentication;

import com.jrest.mvc.model.util.HashingUtil;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.function.UnaryOperator;

/**
 * Представляет кодек аутентификации для обработки учетных данных.
 * <p>
 *     Класс {@code AuthenticationHash} определяет различные кодеки,
 *     используемые для кодирования и декодирования учетных данных при аутентификации.
 *     Каждый кодек предоставляет операторы для кодирования и декодирования данных.
 * </p>
 * <p>
 *     Этот класс может быть использован для настройки аутентификационных механизмов
 *     в верхнеуровневых компонентах системы.
 * </p>
 */
@Getter
@Builder
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuthenticationHash {

    /**
     * Кодек аутентификации без операций кодирования и декодирования.
     * <p>
     *     Этот кодек не выполняет никаких операций с учетными данными,
     *     предоставляя исходные данные как есть.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     AuthenticationHash hash = AuthenticationHash.NO_HASH;
     *     }</pre>
     * </p>
     */
    public static final AuthenticationHash NO_HASH = AuthenticationHash.builder()
            .name("<no-hash>")
            .decodeOperator(UnaryOperator.identity())
            .encodeOperator(UnaryOperator.identity())
            .build();

    /**
     * Кодек аутентификации Base64.
     * <p>
     *     Base64 кодек используется для кодирования и декодирования учетных данных
     *     в формате Base64, который часто используется для передачи данных в HTTP-заголовках.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     AuthenticationHash hash = AuthenticationHash.BASE64;
     *     }</pre>
     * </p>
     */
    public static final AuthenticationHash BASE64 = AuthenticationHash.builder()
            .name("Base64")
            .decodeOperator(HashingUtil::decodeBase64)
            .encodeOperator(HashingUtil::encodeBase64)
            .build();

    /**
     * Кодек аутентификации SHA-256.
     * <p>
     *     SHA-256 кодек используется для кодирования учетных данных с использованием
     *     алгоритма хэширования SHA-256.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     AuthenticationHash hash = AuthenticationHash.SHA256;
     *     }</pre>
     * </p>
     */
    public static final AuthenticationHash SHA256 = AuthenticationHash.builder()
            .name("SHA256")
            .encodeOperator(HashingUtil::encodeSha256)
            .build();

    /**
     * Кодек аутентификации SHA-512.
     * <p>
     *     SHA-512 кодек используется для кодирования учетных данных с использованием
     *     алгоритма хэширования SHA-512.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     AuthenticationHash hash = AuthenticationHash.SHA512;
     *     }</pre>
     * </p>
     */
    public static final AuthenticationHash SHA512 = AuthenticationHash.builder()
            .name("SHA512")
            .encodeOperator(HashingUtil::encodeSha512)
            .build();

    @ToString.Include
    @EqualsAndHashCode.Include
    private final String name;

    private final UnaryOperator<String> decodeOperator;
    private final UnaryOperator<String> encodeOperator;
}
