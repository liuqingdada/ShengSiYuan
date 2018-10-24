package com.java.util.function;

import com.java.Sneaky;

import java.util.function.IntUnaryOperator;

@FunctionalInterface
public interface UncheckedIntUnaryOperator {

    int applyAsInt(int operand) throws Exception;

    static IntUnaryOperator unchecked(UncheckedIntUnaryOperator operator) {
        return operand -> {
            try {
                return operator.applyAsInt(operand);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
