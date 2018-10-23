package com.java.util.function;

import java.util.function.Supplier;

@FunctionalInterface
public interface UncheckedSupplier<T> {

    T get() throws Exception;

    static <T> Supplier<T> unchecked(UncheckedSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
