package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.Predicate;

@FunctionalInterface
public interface UncheckedPredicate<T> {

    boolean test(T t) throws Exception;

    static <T> Predicate<T> unchecked(UncheckedPredicate<T> predicate) {
        return t -> {
            try {
                return predicate.test(t);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
