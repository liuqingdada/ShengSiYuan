package com.java.util.function;

import com.java.Sneaky;

import java.util.function.DoubleConsumer;

@FunctionalInterface
public interface UncheckedDoubleConsumer {

    void accept(double value) throws Exception;

    static DoubleConsumer unchecked(UncheckedDoubleConsumer consumer) {
        return (value -> {
            try {
                consumer.accept(value);

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        });
    }
}
