package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.Function;

@FunctionalInterface
public interface UncheckedFunction<T, R> {

    R apply(T t) throws Exception;

    static <T, R> Function<T, R> unchecked(UncheckedFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception ex) {
                return Sneaky.sneakyThrow(ex);
            }
        };
    }
}
