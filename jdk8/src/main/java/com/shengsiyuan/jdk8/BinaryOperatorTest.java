package com.shengsiyuan.jdk8;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {

    public static void main(String[] args) {
        BinaryOperatorTest test = new BinaryOperatorTest();

        System.out.println(test.operate(2, 3, (a, b) -> a + b));

        System.out.println("---------");

        System.out.println(test.minBy("hello123", "world", (a, b) -> a.length() - b.length()));

        System.out.println(test.minBy("hello", "world", (a, b) -> a.charAt(0) - b.charAt(0)));
    }

    private int operate(int a, int b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }

    private String minBy(String a, String b, Comparator<String> comparator) {
        return BinaryOperator.minBy(comparator)
                             .apply(a, b);
    }
}
