package com.shengsiyuan.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test3 {

    private interface TheInterface {
        void method();
    }

    private interface TheInterface2 {
        void method();
    }

    public static void main(String[] args) {
        TheInterface theInterface = () -> {
        };
        TheInterface2 theInterface2 = () -> {
        };

        System.out.println(theInterface.getClass()
                                       .getInterfaces()[0]);
        System.out.println(theInterface2.getClass()
                                        .getInterfaces()[0]);

        System.out.println("--------------------");

        new Thread(() -> {
            System.out.println(Thread.currentThread()
                                     .getName());
        }).start();

        List<String> list = Arrays.asList("hello", "world", "hello world");

        list.forEach(item -> {
            System.out.println(item.toUpperCase());
        });

        System.out.println("------------");

        list.stream()
            .map(item -> item.toUpperCase())
            .forEach(item -> System.out.println(item));

        System.out.println("------------------");

        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass()
                                   .getInterfaces()[0]);


    }

}
