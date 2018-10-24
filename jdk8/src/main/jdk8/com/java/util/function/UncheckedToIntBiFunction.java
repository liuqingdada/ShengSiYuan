package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.ToIntBiFunction;

@FunctionalInterface
public interface UncheckedToIntBiFunction<T, U> {

    int applyAsInt(T t, U u) throws Exception;

    static <T, U> ToIntBiFunction<T, U> unchecked(UncheckedToIntBiFunction<T, U> function) {
        return (t, u) -> {
            try {
                return function.applyAsInt(t, u);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
