package com.java.util.function;

import com.java.Sneaky;

import java.util.function.DoubleBinaryOperator;

@FunctionalInterface
public interface UncheckedDoubleBinaryOperator {

    double applyAsDouble(double left, double right) throws Exception;

    static DoubleBinaryOperator unchecked(UncheckedDoubleBinaryOperator doubleBinaryOperator) {
        return (left, right) -> {
            try {
                return doubleBinaryOperator.applyAsDouble(left, right);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
