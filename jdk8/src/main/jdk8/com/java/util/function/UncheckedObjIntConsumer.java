package com.java.util.function;

import java.util.function.ObjIntConsumer;

@FunctionalInterface
public interface UncheckedObjIntConsumer<T> {

    void accept(T t, int value) throws Exception;

    static <T> ObjIntConsumer<T> unchecked(UncheckedObjIntConsumer<T> consumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }
}
