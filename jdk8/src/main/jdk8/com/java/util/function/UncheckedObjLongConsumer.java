package com.java.util.function;

import com.java.util.Sneaky;

import java.util.function.ObjLongConsumer;

@FunctionalInterface
public interface UncheckedObjLongConsumer<T> {

    void accept(T t, long value) throws Exception;

    static <T> ObjLongConsumer<T> unchecked(UncheckedObjLongConsumer<T> consumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }
}
