package com.java.util.function;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface UncheckedBiConsumer<T, U> {

    void accept(T t, U u) throws Exception;

    static <T, U> BiConsumer<T, U> unchecked(UncheckedBiConsumer<T, U> biConsumer) {
        return (t, u) -> {
            try {
                biConsumer.accept(t, u);

            } catch (Exception e) {
                Sneaky.sneakyVoidThrow(e);
            }
        };
    }
}
