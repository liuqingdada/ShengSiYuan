package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.LongToDoubleFunction;

@FunctionalInterface
public interface UncheckedLongToDoubleFunction {

    double applyAsDouble(long value) throws Exception;

    static LongToDoubleFunction unchecked(UncheckedLongToDoubleFunction function) {
        return value -> {
            try {
                return function.applyAsDouble(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
