package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest9 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello",
                                          "hello welcome");
        list.stream()
            .flatMap(item -> Stream.of(item.split(" ")))
            .collect(Collectors.toSet())
            .forEach(System.out::println);

        System.out.println("==========");

        list.stream()
            .map(item -> item.split(" "))
            .flatMap(Arrays::stream)
            .distinct()
            .collect(Collectors.toList())
            .forEach(System.out::println);

    }
}
