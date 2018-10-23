package com.java.util.function;

import java.util.function.LongSupplier;

@FunctionalInterface
public interface UncheckedLongSupplier {

    long getAsLong() throws Exception;

    static LongSupplier unchecked(UncheckedLongSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsLong();

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
