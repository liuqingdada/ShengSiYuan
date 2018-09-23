package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "hello world");

        String[] strs = { "hello", "world", "hello world" };
        stream = Arrays.stream(strs);

        StreamTest test = new StreamTest();
        test.intStream();

        IntStream.range(3, 8)
                 .forEach(System.out::println);
        System.out.println("----");
        IntStream.rangeClosed(3, 8)
                 .forEach(System.out::println);

        System.out.println("=============");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        long sum = list.stream()
                       .map((i) -> 2 * i)
                       //.reduce(0, (i, j) -> i + j);
                       .reduce(0, Integer::sum);
        System.out.println(sum);
    }

    private void intStream() {
        IntConsumer intConsumer = (i) -> {
            System.out.println(this.getClass()
                                   .getName());
            System.out.println(i);
        };
        intConsumer.accept(0);

        IntStream.of(1)
                 .forEach(new IntConsumer() {
                     @Override
                     public void accept(int value) {
                         System.out.println(this.getClass()
                                                .getName());
                         System.out.println(value);
                     }
                 });

        IntStream.of(2)
                 .forEach(item -> {
                     System.out.println(this.getClass()
                                            .getName());
                     System.out.println(item);
                 });
    }
}
