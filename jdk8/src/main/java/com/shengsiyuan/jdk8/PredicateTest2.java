package com.shengsiyuan.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PredicateTest2 {

    public static void main(String[] args) {
        PredicateTest2 test = new PredicateTest2();
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        test.conditionFilter(list, item -> item % 2 == 0);
        System.out.println("---------");
        test.conditionFilter(list, item -> item % 2 != 0);
        System.out.println("----------");
        test.conditionFilter(list, item -> item > 5);
        System.out.println(("--------"));
        test.conditionFilter(list, item -> item < 3);

        System.out.println("--------");

        test.conditionFilter(list, item -> true);

        System.out.println("------------");

        list.stream()
            .filter(item -> item > 5)
            .collect(Collectors.toList())
            .forEach(System.out::println);

        System.out.println("=======================");

        test.conditionFilter(list, p1 -> p1 > 5, p2 -> p2 % 2 == 0);

        System.out.println("-------");

        System.out.println(Predicate.isEqual("123")
                                    .test("123"));

    }

    private void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(i -> {
            if (predicate.test(i)) {
                System.out.println(i);
            }
        });
    }

    private void conditionFilter(List<Integer> list, Predicate<Integer> p1, Predicate<Integer> p2) {
        list.forEach(i -> {
            if (p1.and(p2)
                  .negate()
                  .test(i)) {
                System.out.println(i);
            }
        });
    }
}
