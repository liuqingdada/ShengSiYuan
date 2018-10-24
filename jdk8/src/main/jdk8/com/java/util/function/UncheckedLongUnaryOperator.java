package com.java.util.function;

import com.java.Sneaky;

import java.util.function.LongUnaryOperator;

@FunctionalInterface
public interface UncheckedLongUnaryOperator {

    long applyAsLong(long operand) throws Exception;

    static LongUnaryOperator unchecked(UncheckedLongUnaryOperator operator) {
        return operand -> {
            try {
                return operator.applyAsLong(operand);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
