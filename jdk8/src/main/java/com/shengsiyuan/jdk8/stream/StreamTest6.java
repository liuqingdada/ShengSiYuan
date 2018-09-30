package com.shengsiyuan.jdk8.stream;

import java.util.stream.IntStream;

public class StreamTest6 {

    public static void main(String[] args) {
        IntStream.iterate(0, i -> (i + 1) % 2)
                 .limit(6)
                 .distinct() // limit 和 distinct 的顺序在无限流里需要注意
                 .forEach(System.out::println);
    }
}
