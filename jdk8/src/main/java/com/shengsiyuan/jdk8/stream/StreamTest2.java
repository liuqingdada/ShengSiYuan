package com.shengsiyuan.jdk8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest2 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "Hello world");

        String[] array = stream.toArray(String[]::new);
        Arrays.stream(array)
              .forEach(System.out::println);
        System.out.println("-----------");

        stream = Stream.of("hello", "world", "Hello world");
        Set<String> set = stream.collect(Collectors.toSet());

        stream = Stream.of("hello", "world", "Hello world");
        ArrayList<String> collect = stream.parallel()
                                          .collect(() -> {
                                                       System.out.println("param1, " + Thread.currentThread()
                                                                                             .getName());
                                                       return new ArrayList<>();
                                                   },
                                                   (l, item) -> {
                                                       System.out.println(
                                                               "param2, " + Thread.currentThread()
                                                                                  .getName());
                                                       l.add(item);
                                                   },
                                                   (l1, l2) -> {
                                                       System.out.println(
                                                               "param3, " + Thread.currentThread()
                                                                                  .getName());
                                                       l1.addAll(l2);
                                                   });
        System.out.println(collect);

        stream = Stream.of("hello", "world", "Hello world");
        Set<String> lhs = stream.collect(Collectors.toCollection(LinkedHashSet::new));

        stream = Stream.of("hello", "world", "Hello world");
        String str = stream.collect(Collectors.joining());
        System.out.println(str);
    }
}
