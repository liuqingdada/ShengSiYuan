package com.java.util.function;

import com.java.Sneaky;

import java.util.function.DoubleToLongFunction;

@FunctionalInterface
public interface UncheckedDoubleToLongFunction {

    long applyAsLong(double value) throws Exception;

    static DoubleToLongFunction unchecked(UncheckedDoubleToLongFunction function) {
        return value -> {
            try {
                return function.applyAsLong(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
