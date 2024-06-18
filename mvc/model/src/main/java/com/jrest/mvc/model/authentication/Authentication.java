package com.jrest.mvc.model.authentication;

import com.jrest.mvc.model.Headers;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * Представляет тип аутентификации для HTTP-заголовков.
 * <p>
 *     Класс {@code Authentication} определяет различные типы аутентификации,
 *     которые могут использоваться в HTTP-запросах и ответах. Каждый тип аутентификации
 *     имеет свой кодек и список заголовков, с которыми он совместим.
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
public class Authentication {

    /**
     * Тип аутентификации без аутентификации.
     * <p>
     *     Этот тип аутентификации не требует предоставления учетных данных
     *     и принимает определенные HTTP-заголовки для обработки запросов.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     Authentication authType = Authentication.NO_AUTHENTICATION;
     *     }</pre>
     * </p>
     */
    public static final Authentication NO_AUTHENTICATION = Authentication.builder()
            .name("")
            .acceptableHeaders(Arrays.asList(
                    Headers.Def.AUTHORIZATION,
                    Headers.Def.WWW_AUTHENTICATE,
                    Headers.Def.PROXY_AUTHENTICATE,
                    Headers.Def.PROXY_AUTHORIZATION))
            .hash(AuthenticationHash.NO_HASH)
            .build();

    /**
     * Тип аутентификации Basic.
     * <p>
     *     Basic аутентификация использует заголовок {@code Authorization},
     *     чтобы передавать учетные данные в формате Base64.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     Authentication authType = Authentication.BASIC;
     *     }</pre>
     * </p>
     */
    public static final Authentication BASIC = Authentication.builder()
            .name("Basic")
            .acceptableHeaders(Arrays.asList(
                    Headers.Def.AUTHORIZATION,
                    Headers.Def.PROXY_AUTHORIZATION))
            .hash(AuthenticationHash.BASE64)
            .build();

    /**
     * Тип аутентификации Bearer.
     * <p>
     *     Bearer аутентификация использует заголовок {@code Authorization},
     *     чтобы передавать токен доступа без кодирования.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     Authentication authType = Authentication.BEARER;
     *     }</pre>
     * </p>
     */
    public static final Authentication BEARER = Authentication.builder()
            .name("Bearer")
            .acceptableHeaders(Arrays.asList(
                    Headers.Def.AUTHORIZATION,
                    Headers.Def.PROXY_AUTHORIZATION))
            .hash(AuthenticationHash.NO_HASH)
            .build();

    /**
     * Тип аутентификации Digest.
     * <p>
     *     Digest аутентификация используется для передачи учетных данных
     *     в зашифрованном формате через заголовок {@code WWW-Authenticate}.
     * </p>
     * <p>
     *     Пример использования:
     *     <pre>{@code
     *     Authentication authType = Authentication.DIGEST;
     *     }</pre>
     * </p>
     */
    public static final Authentication DIGEST = Authentication.builder()
            .name("Digest")
            .acceptableHeaders(Collections.singletonList(Headers.Def.WWW_AUTHENTICATE))
            .hash(AuthenticationHash.SHA256)
            .build();

    private final AuthenticationHash hash;
    private final List<String> acceptableHeaders;

    @ToString.Include
    @EqualsAndHashCode.Include
    private final String name;

    /**
     * Форматирует токен с использованием текущего типа аутентификации.
     * <p>
     * Этот метод кодирует токен в соответствии с хеш-функцией, если она определена,
     * и возвращает строку в формате "name token".
     * </p>
     *
     * @param token Токен, который необходимо форматировать.
     * @return Отформатированная строка, представляющая аутентификационный заголовок.
     */
    public String format(Object token) {
        UnaryOperator<String> encodeOperator = hash.getEncodeOperator();
        String tokenString = token.toString();

        if (encodeOperator != null) {
            tokenString = encodeOperator.apply(tokenString);
        }

        return String.format("%s %s", name, tokenString);
    }
}
