package com.java.util.function;

import com.java.Sneaky;

import java.util.function.IntSupplier;

@FunctionalInterface
public interface UncheckedIntSupplier {

    int getAsInt() throws Exception;

    static IntSupplier unchecked(UncheckedIntSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsInt();

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
