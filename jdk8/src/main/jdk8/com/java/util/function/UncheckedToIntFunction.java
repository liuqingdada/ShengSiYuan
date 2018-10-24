package com.java.util.function;

import com.java.Sneaky;

import java.util.function.ToIntFunction;

@FunctionalInterface
public interface UncheckedToIntFunction<T> {

    int applyAsInt(T value) throws Exception;

    static <T> ToIntFunction<T> unchecked(UncheckedToIntFunction<T> function) {
        return value -> {
            try {
                return function.applyAsInt(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
