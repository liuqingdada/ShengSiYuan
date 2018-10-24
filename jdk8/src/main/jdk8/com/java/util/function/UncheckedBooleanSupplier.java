package com.java.util.function;

import com.java.Sneaky;

import java.util.function.BooleanSupplier;

@FunctionalInterface
public interface UncheckedBooleanSupplier {

    boolean getAsBoolean() throws Exception;

    static BooleanSupplier unchecked(UncheckedBooleanSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsBoolean();

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
