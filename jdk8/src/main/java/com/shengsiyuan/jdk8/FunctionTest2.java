package com.shengsiyuan.jdk8;

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

    public static void main(String[] args) {
        FunctionTest2 test = new FunctionTest2();
        int compute = test.compute(2, v -> v * 3, v -> v * v);
        System.out.println(compute);

        compute = test.compute2(2, v -> v * 3, v -> v * v);
        System.out.println(compute);

        compute = test.compute2(3, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 3;
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        });
    }
}
