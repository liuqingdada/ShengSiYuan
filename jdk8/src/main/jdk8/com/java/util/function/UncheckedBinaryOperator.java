package com.java.util.function;

import com.java.Sneaky;

import java.util.function.BinaryOperator;

public interface UncheckedBinaryOperator<T> extends UncheckedBiFunction<T, T, T> {

    static <T> BinaryOperator<T> unchecked(UncheckedBinaryOperator<T> binaryOperator) {
        return (t, u) -> {
            try {
                return binaryOperator.apply(t, u);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
