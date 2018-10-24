package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.UnaryOperator;

@FunctionalInterface
public interface UncheckedUnaryOperator<T> extends UncheckedFunction<T, T> {

    static <T> UnaryOperator<T> unchecked(UncheckedUnaryOperator<T> operator) {
        return t -> {
            try {
                return operator.apply(t);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
