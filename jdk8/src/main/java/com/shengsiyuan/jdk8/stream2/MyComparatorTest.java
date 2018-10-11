package com.shengsiyuan.jdk8.stream2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MyComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");

        list.stream()
            .sorted(Comparator.comparing(String::length))
            .forEach(System.out::println);
        System.out.println("-----------------");

        list.stream()
            .sorted(Comparator.comparingInt(String::length)
                              .reversed())
            .forEach(System.out::println);
        System.out.println("-----------------");

        list.stream()
            .sorted(Comparator.comparingInt((String item) -> item.length())
                              .reversed())
            .forEach(System.out::println);
        System.out.println("------------");

        list.stream()
            .sorted(Comparator.comparingInt(String::length)
                              .reversed()
                              .thenComparing(String.CASE_INSENSITIVE_ORDER))
            .forEach(System.out::println);
        System.out.println("------");

        list.stream()
            .sorted(Comparator.comparingInt(String::length)
                              .reversed()
                              .thenComparing(String::compareToIgnoreCase))
            .forEach(System.out::println);
        System.out.println("------");

        list.stream()
            .sorted(Comparator.comparingInt(String::length)
                              .reversed()
                              .thenComparing(Comparator.comparing(String::toLowerCase)))
            .forEach(System.out::println);
        System.out.println("------");

        list.stream()
            .sorted(Comparator.comparingInt(String::length)
                              .reversed()
                              .thenComparing(Comparator.comparing(String::toLowerCase,
                                                                  Comparator.reverseOrder())))
            .forEach(System.out::println);
        System.out.println("------");

        list.stream()
            .sorted(Comparator.comparingInt(String::length)
                              .reversed()
                              .thenComparing(String::toLowerCase, Comparator.reverseOrder()))
            .forEach(System.out::println);
        System.out.println("==============");



        System.out.println("\n**********************");
        System.out.println(list);
    }
}
