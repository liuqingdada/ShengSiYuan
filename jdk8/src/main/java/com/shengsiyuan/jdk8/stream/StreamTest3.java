package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "Hello World", "TEST");

        list.stream()
            .map(String::toUpperCase)
            .forEach(System.out::println); // 打印的是流的中间操作变量
        System.out.println(list);

        System.out.println("---------------");

        Stream.of(Collections.singletonList(1), Arrays.asList(2, 3), Arrays.asList(4, 5, 6))
              .flatMap(List::stream)
              .map(item -> item * item)
              .forEach(System.out::println);
    }
}
