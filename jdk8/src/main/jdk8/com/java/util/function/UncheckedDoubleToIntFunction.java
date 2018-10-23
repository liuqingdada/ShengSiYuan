package com.java.util.function;

import java.util.function.DoubleToIntFunction;

@FunctionalInterface
public interface UncheckedDoubleToIntFunction {

    int applyAsInt(double value) throws Exception;

    static DoubleToIntFunction unchecked(UncheckedDoubleToIntFunction function) {
        return value -> {
            try {
                return function.applyAsInt(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
