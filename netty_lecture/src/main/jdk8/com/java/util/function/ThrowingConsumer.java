package com.java.util.function;

import java.util.function.Consumer;

public interface ThrowingConsumer<T> {
    void accept(T t) throws Exception;

    static <T> Consumer<T> unchecked(ThrowingConsumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);

            } catch (Exception e) {
                sneakyThrow(e);
            }
        };
    }

    @SuppressWarnings("unchecked")
    static <T extends Exception> void sneakyThrow(Exception t) throws T {
        throw (T) t; // ( ͡° ͜ʖ ͡°)
    }
}
