package com.java.util.function;

import java.util.function.DoubleSupplier;

@FunctionalInterface
public interface UncheckedDoubleSupplier {

    double getAsDouble() throws Exception;

    static DoubleSupplier unchecked(UncheckedDoubleSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsDouble();

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
