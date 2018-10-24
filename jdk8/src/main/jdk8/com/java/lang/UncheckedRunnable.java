package com.java.lang;

import com.java.Sneaky;

@FunctionalInterface
public interface UncheckedRunnable {

    void run() throws Exception;

    static Runnable unchecked(UncheckedRunnable runnable) {
        return () -> {
            try {
                runnable.run();

            } catch (Exception e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }
}
