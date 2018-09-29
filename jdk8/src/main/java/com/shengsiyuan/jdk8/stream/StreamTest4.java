package com.shengsiyuan.jdk8.stream;

import java.util.IntSummaryStatistics;
import java.util.UUID;
import java.util.stream.Stream;

public class StreamTest4 {

    public static void main(String[] args) {
        Stream.generate(UUID::randomUUID)
              .map(UUID::toString)
              .findFirst()
              .ifPresent(System.out::println);

        Stream.iterate(1, item -> item + 2)
              .limit(6)
              .forEach(System.out::println);

        System.out.println("======");

        IntSummaryStatistics iss = Stream.iterate(1, item -> item + 2)
                                         .limit(6)
                                         .filter(item -> item > 2)
                                         .mapToInt(item -> item * 2)
                                         .skip(2)
                                         .limit(2)
                                         .summaryStatistics();
        //System.out.println(sum);


    }
}
