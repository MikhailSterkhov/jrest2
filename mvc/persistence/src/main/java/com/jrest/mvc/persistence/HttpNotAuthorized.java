package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания метода о запросе, которым не требуется аутентификации
 * <p>
 * Используется для обозначения методов, для которых обработку авторизации
 * применять не нужно или необязательно.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpNotAuthorized
 * @HttpGet("/list")
 * public HttpResponse listGet(HttpRequest request) {
 *     // Логика запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpNotAuthorized {
}
