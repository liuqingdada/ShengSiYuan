package com.java.util.function;

import java.util.function.IntPredicate;

@FunctionalInterface
public interface UncheckedIntPredicate {

    boolean test(int value) throws Exception;

    static IntPredicate unchecked(UncheckedIntPredicate predicate) {
        return value -> {
            try {
                return predicate.test(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
