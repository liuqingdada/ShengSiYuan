package com.java.util.function;

import com.java.Sneaky;

import java.util.function.BiFunction;

@FunctionalInterface
public interface UncheckedBiFunction<T, U, R> {

    R apply(T t, U u) throws Exception;

    static <T, U, R> BiFunction<T, U, R> unchecked(UncheckedBiFunction<T, U, R> biFunction) {
        return (t, u) -> {
            try {
                return biFunction.apply(t, u);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
