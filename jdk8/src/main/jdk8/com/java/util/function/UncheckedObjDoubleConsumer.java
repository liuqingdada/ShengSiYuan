package com.java.util.function;

import com.java.Sneaky;

import java.util.function.ObjDoubleConsumer;

@FunctionalInterface
public interface UncheckedObjDoubleConsumer<T> {

    void accept(T t, double value) throws Exception;

    static <T> ObjDoubleConsumer<T> unchecked(UncheckedObjDoubleConsumer<T> consumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }
}
