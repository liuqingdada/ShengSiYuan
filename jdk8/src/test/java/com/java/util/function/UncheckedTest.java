package com.java.util.function;

import org.junit.Test;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;

public class UncheckedTest {

    @Test
    public void biConsumer() {
        BiConsumer<String, String> biConsumer = UncheckedBiConsumer.unchecked(
                (a, b) -> {
                    //                    System.out.println(a + b);
                    throw new RuntimeException();
                });
        biConsumer.accept("a", "b");
    }

    @Test
    public void biFunction() {
        BiFunction<String, String, String> biFunction = UncheckedBiFunction.unchecked(
                (a, b) -> {
                    //                    return a + b;
                    throw new RuntimeException();
                });
        String apply = biFunction.apply("a", "b");
        System.out.println(apply);
    }

    @Test
    public void uncheckedBinaryOperator() {
        BinaryOperator<String> binaryOperator = UncheckedBinaryOperator.unchecked((a, b) -> {
            //            return a + b;
            throw new RuntimeException();
        });
        String apply = binaryOperator.apply("a", "b");
        System.out.println(apply);
    }

    @Test
    public void uncheckedBiPredicate() {
        BiPredicate<String, String> biPredicate = UncheckedBiPredicate.unchecked(
                (a, b) -> {
                    throw new RuntimeException();
                    //                     return a.equals(b);
                });
        boolean test = biPredicate.test("a", "b");
        System.out.println(test);
    }

}
