package com.java.util.function;

import java.util.function.IntConsumer;

@FunctionalInterface
public interface UncheckedIntConsumer {

    void accept(int value) throws Exception;

    static IntConsumer unchecked(UncheckedIntConsumer consumer) {
        return value -> {
            try {
                consumer.accept(value);

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }
}
