package com.java.util.function;

import com.java.Sneaky;

import java.util.function.DoubleUnaryOperator;

@FunctionalInterface
public interface UncheckedDoubleUnaryOperator {

    double applyAsDouble(double operand) throws Exception;

    static DoubleUnaryOperator unchecked(UncheckedDoubleUnaryOperator operator) {
        return operand -> {
            try {
                return operator.applyAsDouble(operand);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
