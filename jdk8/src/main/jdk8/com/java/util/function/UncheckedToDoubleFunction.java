package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.ToDoubleFunction;

@FunctionalInterface
public interface UncheckedToDoubleFunction<T> {

    double applyAsDouble(T value) throws Exception;

    static <T> ToDoubleFunction<T> unchecked(UncheckedToDoubleFunction<T> function) {
        return value -> {
            try {
                return function.applyAsDouble(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
