package com.shengsiyuan.jdk8.stream2;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class MySetCollector2<T> implements Collector<T, Set<T>, Map<T, T>> {

    @Override
    public Supplier<Set<T>> supplier() {
        System.out.println("supplier invocked!");
        return HashSet::new;
    }

    @Override
    public BiConsumer<Set<T>, T> accumulator() {
        System.out.println("accumulator invocked!");
        return (set, item) -> {
            System.out.println("accumulator: " + Thread.currentThread()
                                                       .getName());
            set.add(item);
        };
    }

    @Override
    public BinaryOperator<Set<T>> combiner() {
        System.out.println("combiner invocked!");
        return (s1, s2) -> {
            System.out.println("combiner: " + Thread.currentThread()
                                                    .getName());
            s1.addAll(s2);
            return s1;
        };
    }

    @Override
    public Function<Set<T>, Map<T, T>> finisher() {
        System.out.println("finisher invocked!");
        Map<T, T> map = new TreeMap<>();
        return set -> {
            set.forEach(item -> map.put(item, item));
            return map;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        System.out.println("characteristics invocked!");
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED));
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime()
                                  .availableProcessors());
        List<String> list = Arrays.asList("hello", "world", "hello world", "a", "b", "c");
        Set<String> set = new HashSet<>(list);
        System.out.println(set);

        Map<String, String> map = set.stream()
                                     .parallel()
                                     .collect(new MySetCollector2<>());
        System.out.println(map);

    }
}
