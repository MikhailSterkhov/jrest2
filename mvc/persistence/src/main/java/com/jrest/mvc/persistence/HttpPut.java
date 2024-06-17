package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода HTTP PUT.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP PUT запросы.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpPut("/put-endpoint")
 * public void putMethod() {
 *     // Логика обработки PUT запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpPut {

    String value() default "/";
}
