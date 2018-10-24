package com.java.util.function;

import com.java.Sneaky;

import java.util.function.LongFunction;

@FunctionalInterface
public interface UncheckedLongFunction<R> {

    R apply(long value) throws Exception;

    static <R> LongFunction<R> unchecked(UncheckedLongFunction<R> function) {
        return value -> {
            try {
                return function.apply(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
