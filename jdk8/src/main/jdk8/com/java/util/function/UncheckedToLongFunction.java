package com.java.util.function;

import com.java.Sneaky;

import java.util.function.ToLongFunction;

@FunctionalInterface
public interface UncheckedToLongFunction<T> {

    long applyAsLong(T value) throws Exception;

    static <T> ToLongFunction<T> unchecked(UncheckedToLongFunction<T> function) {
        return value -> {
            try {
                return function.applyAsLong(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
