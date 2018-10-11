package com.shengsiyuan.jdk8.stream2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");

        try (Stream<String> stream = list.stream()) {
            stream.sequential()
                  .onClose(() -> {
                      System.out.println("runnable onclose 1");
                      throw new RuntimeException();
                  })
                  .onClose(() -> {
                      System.out.println("runnable onclose 2");
                      throw new ArithmeticException();
                  })
                  .onClose(() -> {
                      System.out.println("runnable onclose 3");
                      throw new NullPointerException();
                  })
                  .forEach(System.out::println);
        }


    }
}
