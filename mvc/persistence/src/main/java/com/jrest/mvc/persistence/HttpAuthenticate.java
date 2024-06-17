package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода аутентификации HTTP запроса.
 * <p>
 * Используется для обозначения методов, к которым необходимо применять
 * аутентификацию HTTP запросов.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpAuthenticate
 * @HttpPost("/create_user")
 * public void doPost() {
 *     // Логика аутентификации запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpAuthenticate {
}
