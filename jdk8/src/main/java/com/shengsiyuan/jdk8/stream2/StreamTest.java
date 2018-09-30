package com.shengsiyuan.jdk8.stream2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        Student zhangsan = new Student("zhangsan", 80);
        Student lisi = new Student("lisi", 90);
        Student wangwu = new Student("wangwu", 100);
        Student zhaoliu = new Student("zhaoliu", 90);

        List<Student> students = Arrays.asList(zhangsan, lisi, wangwu, zhaoliu);

        students.stream()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("===============");

        System.out.println(students.stream()
                                   .collect(Collectors.counting()));
        System.out.println(students.stream()
                                   .count());
    }
}
