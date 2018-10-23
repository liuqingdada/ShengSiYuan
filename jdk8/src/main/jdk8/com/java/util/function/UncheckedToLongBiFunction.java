package com.java.util.function;

import java.util.function.ToLongBiFunction;

@FunctionalInterface
public interface UncheckedToLongBiFunction<T, U> {

    long applyAsLong(T t, U u) throws Exception;

    static <T, U> ToLongBiFunction<T, U> unchecked(UncheckedToLongBiFunction<T, U> function) {
        return (t, u) -> {
            try {
                return function.applyAsLong(t, u);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
