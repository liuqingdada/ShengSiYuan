package com.shengsiyuan.jdk8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class StreamTest7 {

    public static void main(String[] args) {
        int size = 5000000;

        List<String> list = new ArrayList<>(size);

        String uuid;
        for (int i = 0; i < size; i++) {
            uuid = UUID.randomUUID()
                       .toString();
            list.add(uuid);
        }

        System.out.println("start sort...");

        long startTime = System.nanoTime();

        list.stream()
            .parallel()
            .sorted()
            .count();

        long endTime = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("take time: " + millis);
    }
}
