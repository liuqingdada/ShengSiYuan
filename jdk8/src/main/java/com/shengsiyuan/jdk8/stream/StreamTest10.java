package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest10 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hi", "hello", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu", "zhaoliu");

        list.stream()
            .flatMap(item -> list2.stream()
                                  .map(item2 -> item + " " + item2))
            .collect(Collectors.toList())
            .forEach(System.out::println);
    }
}
