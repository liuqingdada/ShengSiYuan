package com.java.util.function;

import com.java.Sneaky;

import java.util.function.IntFunction;

@FunctionalInterface
public interface UncheckedIntFunction<R> {

    R apply(int value) throws Exception;

    static <R> IntFunction<R> unchecked(UncheckedIntFunction<R> function) {
        return value -> {
            try {
                return function.apply(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
