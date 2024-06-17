package com.jrest.mvc.persistence;

import java.lang.annotation.*;

/**
 * Аннотация для указания HTTP сервера.
 * <p>
 * Используется для обозначения классов, которые представляют собой HTTP сервер.
 * </p>
 *
 * <pre>
 * {@code
 * @HttpServer
 * public class MyHttpServer {
 *     // Логика HTTP сервера
 * }
 * }
 * </pre>
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HttpServer {
}
