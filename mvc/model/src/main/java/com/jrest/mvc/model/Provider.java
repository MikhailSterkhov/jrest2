package com.jrest.mvc.model;

public interface Provider<T> {

    T get();

    void set(T value);
}
