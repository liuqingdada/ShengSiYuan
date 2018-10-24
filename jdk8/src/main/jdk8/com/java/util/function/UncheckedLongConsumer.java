package com.java.util.function;

import java.util.function.LongConsumer;

@FunctionalInterface
public interface UncheckedLongConsumer {

    void accept(long value) throws Exception;

    static LongConsumer unchecked(UncheckedLongConsumer consumer) {
        return (value) -> {
            try {
                consumer.accept(value);

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }
}
