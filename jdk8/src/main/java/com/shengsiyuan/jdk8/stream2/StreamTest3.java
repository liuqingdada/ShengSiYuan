package com.shengsiyuan.jdk8.stream2;

import java.util.Arrays;
import java.util.List;

public class StreamTest3 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3", "4");

        list.stream()
            .forEach(System.out::println);
    }
}
