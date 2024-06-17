package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода HTTP DELETE.
 * <p>
 * Используется для обозначения методов, обрабатывающих HTTP DELETE запросы.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpDelete("/delete-endpoint")
 * public void deleteMethod() {
 *     // Логика обработки DELETE запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpDelete {

    String value() default "/";
}
