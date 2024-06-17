package com.jrest.mvc.persistence;

import java.lang.annotation.*;


/**
 * Аннотация для указания метода HTTP GET.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP GET запросы.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpGet("/get-endpoint")
 * public void getMethod() {
 *     // Логика обработки GET запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpGet {

    String value() default "/";
}
