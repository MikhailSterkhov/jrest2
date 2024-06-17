package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для маппинга HTTP запросов.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP запросы с заданным методом и путем.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpRequestMapping(method = "GET", path = "/example")
 * public void exampleMethod() {
 *     // Логика обработки GET запроса на /example
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpRequestMapping {

    String method() default "*";

    String path() default "*";
}
