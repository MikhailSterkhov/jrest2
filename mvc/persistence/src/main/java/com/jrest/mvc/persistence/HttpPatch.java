package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода HTTP PATCH.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP PATCH запросы.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpPatch("/patch-endpoint")
 * public void patchMethod() {
 *     // Логика обработки PATCH запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpPatch {

    String value() default "/";
}
