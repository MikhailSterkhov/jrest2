package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода HTTP CONNECT.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP CONNECT запросы.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpConnect("/connect-endpoint")
 * public void connectMethod() {
 *     // Логика обработки CONNECT запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpConnect {

    String value() default "/";
}
