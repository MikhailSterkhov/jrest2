package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания асинхронного метода HTTP.
 * <p>
 * Используется для обозначения методов, которые выполняются асинхронно при обработке HTTP запросов.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpAsync
 * @HttpRequestMapping
 * public HttpResponse asyncMethod(HttpRequest) {
 *     // Асинхронная логика обработки HTTP запроса
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface HttpAsync {
}
