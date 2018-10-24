package com.java.util.function;

import com.java.Sneaky;

import java.util.function.LongBinaryOperator;

@FunctionalInterface
public interface UncheckedLongBinaryOperator {

    long applyAsLong(long left, long right) throws Exception;

    static LongBinaryOperator unchecked(UncheckedLongBinaryOperator operator) {
        return (left, right) -> {
            try {
                return operator.applyAsLong(left, right);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
