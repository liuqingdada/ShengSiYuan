package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.IntToLongFunction;

@FunctionalInterface
public interface UncheckedIntToLongFunction {

    long applyAsLong(int value) throws Exception;

    static IntToLongFunction unchecked(UncheckedIntToLongFunction function) {
        return value -> {
            try {
                return function.applyAsLong(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
