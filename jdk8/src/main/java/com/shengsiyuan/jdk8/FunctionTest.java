package com.shengsiyuan.jdk8;

import java.util.function.Function;

public class FunctionTest {

    private int compute(int a, Function<Integer, Integer> function) {
        return function.apply(a);
    }

    private String convert(int a, Function<Integer, String> function) {
        return function.apply(a);
    }

    public static void main(String[] args) {
        FunctionTest functionTest = new FunctionTest();

        int compute = functionTest.compute(1, value -> {
            return 2 * value;
        });
        System.out.println(compute);

        compute = functionTest.compute(2, value -> {
            return 5 + value;
        });
        System.out.println(compute);

        compute = functionTest.compute(3, value -> value * value);
        System.out.println(compute);

        System.out.println("-------------");


        String str = functionTest.convert(5, value -> String.valueOf(value + " hello"));
        System.out.println(str);

        str = functionTest.convert(10, String::valueOf);
        System.out.println(str);
    }
}
