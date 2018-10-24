package com.java.util.function;

import com.java.Sneaky;

import java.util.function.DoubleFunction;

@FunctionalInterface
public interface UncheckedDoubleFunction<R> {

    R apply(double value) throws Exception;

    static <R> DoubleFunction<R> unchecked(UncheckedDoubleFunction<R> function) {
        return (value -> {
            try {
                return function.apply(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        });
    }
}
