package com.java.util.function;

import com.java.Sneaky;

import java.util.function.IntBinaryOperator;

@FunctionalInterface
public interface UncheckedIntBinaryOperator {

    int applyAsInt(int left, int right) throws Exception;

    static IntBinaryOperator unchecked(UncheckedIntBinaryOperator operator) {
        return (left, right) -> {
            try {
                return operator.applyAsInt(left, right);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
