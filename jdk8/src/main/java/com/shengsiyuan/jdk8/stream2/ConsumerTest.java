package com.shengsiyuan.jdk8.stream2;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class ConsumerTest {

    public static void main(String[] args) {
        ConsumerTest test = new ConsumerTest();

        Consumer<Integer> consumer = System.out::println;
        IntConsumer intConsumer = System.out::println;

        test.test(consumer);
//        test.test(intConsumer);
        test.test(consumer::accept);
        test.test(intConsumer::accept);
    }

    private void test(Consumer<Integer> consumer){
        consumer.accept(100);
    }
}
