package com.jrest.mvc.persistence;

import com.jrest.mvc.persistence.listener.HttpAuthenticationListener;
import com.jrest.mvc.persistence.listener.HttpExceptionListener;
import com.jrest.mvc.persistence.listener.defaults.HttpInternalErrorExceptionListener;
import com.jrest.mvc.persistence.listener.defaults.HttpNoAuthenticationListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface HttpServer {

    Class<? extends HttpAuthenticationListener> authenticationListener() default HttpNoAuthenticationListener.class;

    Class<? extends HttpExceptionListener> exceptionListener() default HttpInternalErrorExceptionListener.class;
}
