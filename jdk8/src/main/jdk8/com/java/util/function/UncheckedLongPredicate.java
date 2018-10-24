package com.java.util.function;

import com.java.Sneaky;

import java.util.function.LongPredicate;

@FunctionalInterface
public interface UncheckedLongPredicate {

    boolean test(long value) throws Exception;

    static LongPredicate unchecked(UncheckedLongPredicate predicate) {
        return value -> {
            try {
                return predicate.test(value);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
