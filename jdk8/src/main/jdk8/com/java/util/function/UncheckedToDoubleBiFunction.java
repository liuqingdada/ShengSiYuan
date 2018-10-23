package com.java.util.function;

import java.util.function.ToDoubleBiFunction;

@FunctionalInterface
public interface UncheckedToDoubleBiFunction<T, U> {

    double applyAsDouble(T t, U u) throws Exception;

    static <T, U> ToDoubleBiFunction<T, U> unchecked(UncheckedToDoubleBiFunction<T, U> function) {
        return (t, u) -> {
            try {
                return function.applyAsDouble(t, u);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
