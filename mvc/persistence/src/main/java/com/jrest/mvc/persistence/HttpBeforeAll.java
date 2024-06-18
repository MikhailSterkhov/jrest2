package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода, выполняемого перед выполнением HTTP запроса.
 * <p>
 * Используется для обозначения методов, которые должны выполняться перед обработкой HTTP запросов
 * с указанным методом и путем.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpBeforeAll(method = "POST", path = "/getuser")
 * public void beforeExecution() {
 *     // Логика, выполняемая перед выполнением POST запроса на /getuser
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpBeforeAll {

    String method() default "*";

    String path() default "*";
}
