package com.java.util.function;

import java.util.function.Consumer;

@FunctionalInterface
public interface UncheckedConsumer<T> {

    void accept(T t) throws Exception;

    static <T> Consumer<T> unchecked(UncheckedConsumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }
}
