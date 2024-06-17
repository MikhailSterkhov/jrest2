package com.jrest.mvc.persistence;

import com.jrest.mvc.model.Headers;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода аутентификации HTTP запроса.
 * <p>
 * Используется для обозначения методов, которые обрабатывают аутентификацию
 * HTTP запросов с указанным заголовком.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpAuthenticator(header = Headers.Def.PROXY_AUTHORIZATION)
 * public void authenticate() {
 *     // Логика аутентификации запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpAuthenticator {

    String header() default Headers.Def.AUTHORIZATION;
}
