package com.java.util.function;

import com.java.Sneaky;

import java.util.function.LongToIntFunction;

@FunctionalInterface
public interface UncheckedLongToIntFunction {

    int applyAsInt(long value) throws Exception;

    static LongToIntFunction unchecked(UncheckedLongToIntFunction function) {
        return value -> {
            try {
                return function.applyAsInt(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
