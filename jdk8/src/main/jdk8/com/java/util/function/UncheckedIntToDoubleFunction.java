package com.java.util.function;

import java.util.function.IntToDoubleFunction;

@FunctionalInterface
public interface UncheckedIntToDoubleFunction {

    double applyAsDouble(int value) throws Exception;

    static IntToDoubleFunction unchecked(UncheckedIntToDoubleFunction function) {
        return value -> {
            try {
                return function.applyAsDouble(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
