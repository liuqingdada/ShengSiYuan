package com.shengsiyuan.jdk8;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {

        Optional<String> optional = Optional.of("hello");
        if (optional.isPresent()) {
            System.out.println(optional.get());
        }
        optional.ifPresent(hello -> System.out.println(hello));
        optional.ifPresent(System.out::println);

        ////////////////////
        System.out.println("-------------");

        optional = Optional.ofNullable(null);
        if (optional.isPresent()) {
            System.out.println("??????");
            System.out.println(optional.get());
        }
        optional.ifPresent(hello -> System.out.println(hello));
        optional.ifPresent(System.out::println);

        System.out.println(optional.orElse("world"));

        System.out.println(optional.orElseGet(() -> "world"));

        System.out.println("=========================\n");

        optional = Optional.of("hello");
        System.out.println(optional.orElse("world"));
        System.out.println(optional.orElseGet(() -> "world"));
    }
}
