package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода HTTP POST.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP POST запросы.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpPost("/post-endpoint")
 * public void postMethod() {
 *     // Логика обработки POST запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpPost {

    String value() default "/";
}
