package com.java.util;

import com.java.Sneaky;

import java.util.Comparator;

@FunctionalInterface
public interface UncheckedComparator<T> {

    int compare(T o1, T o2) throws Exception;

    static <T> Comparator<T> unchecked(UncheckedComparator<T> comparator) {
        return (o1, o2) -> {
            try {
                return comparator.compare(o1, o2);

            } catch (Exception e) {
                return Sneaky.sneakyThrow(e);
            }
        };
    }
}
