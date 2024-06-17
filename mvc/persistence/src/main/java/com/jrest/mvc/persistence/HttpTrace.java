package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода HTTP TRACE.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP TRACE запросы.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpTrace("/trace-endpoint")
 * public void traceMethod() {
 *     // Логика обработки TRACE запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpTrace {

    String value() default "/";
}
