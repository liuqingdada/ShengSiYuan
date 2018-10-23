package com.java.util.function;

import java.util.function.DoublePredicate;

@FunctionalInterface
public interface UncheckedDoublePredicate {

    boolean test(double value) throws Exception;

    static DoublePredicate unchecked(UncheckedDoublePredicate predicate) {
        return value -> {
            try {
                return predicate.test(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
