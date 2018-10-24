package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.BiPredicate;

@FunctionalInterface
public interface UncheckedBiPredicate<T, U> {

    boolean test(T t, U u) throws Exception;

    static <T, U> BiPredicate<T, U> unchecked(UncheckedBiPredicate<T, U> predicate) {
        return (t, u) -> {
            try {
                return predicate.test(t, u);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
