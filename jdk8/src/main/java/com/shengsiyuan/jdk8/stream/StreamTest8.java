package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.List;

public class StreamTest8 {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("hello", "world", "hello world");

        list.stream()
            .parallel()
            .filter(item -> item.length() == 5)
            .findFirst()
            .ifPresent(item -> {
                System.out.println(item.length());
                System.out.println(item);
            });

        System.out.println("===========");

        list.stream()
            //.parallel()
            .mapToInt(item -> {
                int length = item.length();
                System.out.println(item);
                return length;
            })
            .filter(length -> length == 5)
            .findFirst()
            .ifPresent(System.out::println);
    }
}
