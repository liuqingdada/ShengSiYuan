package com.java.util.function;

import java.util.function.Function;

public interface ThrowingFunction<T, R> {
    R apply(T t) throws Exception;

    static <T, R> Function<T, R> unchecked(ThrowingFunction<T, R> f) {
        return t -> {
            try {
                return f.apply(t);
            } catch (Exception ex) {
                return sneakyThrow(ex);
            }
        };
    }

    @SuppressWarnings("unchecked")
    static <T extends Exception, R> R sneakyThrow(Exception t) throws T {
        throw (T) t; // ( ͡° ͜ʖ ͡°)
    }
}
