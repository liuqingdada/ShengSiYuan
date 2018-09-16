package com.shengsiyuan.jdk8;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionTest2 {

    private int compute(int a,
                        Function<Integer, Integer> function1,
                        Function<Integer, Integer> function2) {
        return function1.compose(function2)
                        .apply(a);
    }

    private int compute2(int a,
                         Function<Integer, Integer> function1,
                         Function<Integer, Integer> function2) {
        return function1.andThen(function2)
                        .apply(a);
    }

    private int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    private int compute4(int a, int b,
                         BiFunction<Integer, Integer, Integer> biFunction,
                         Function<Integer, Integer> function) {
        return biFunction.andThen(function)
                         .apply(a, b);
    }

    public static void main(String[] args) {
        FunctionTest2 test = new FunctionTest2();
        int compute = test.compute(2, v -> v * 3, v -> v * v);
        System.out.println(compute);

        compute = test.compute2(2, v -> v * 3, v -> v * v);
        System.out.println(compute);

        System.out.println("---------");

        compute = test.compute3(1, 2, (v1, v2) -> v1 + v2);
        System.out.println(compute);
        compute = test.compute3(1, 2, (v1, v2) -> v1 - v2);
        System.out.println(compute);
        compute = test.compute3(1, 2, (v1, v2) -> v1 * v2);
        System.out.println(compute);
        compute = test.compute3(1, 2, (v1, v2) -> v1 / v2);
        System.out.println(compute);

        System.out.println("------------");

        compute = test.compute4(2, 3, (v1, v2) -> v1 + v2, v -> v * v);
        System.out.println(compute);
    }
}
