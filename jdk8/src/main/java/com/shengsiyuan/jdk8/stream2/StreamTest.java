package com.shengsiyuan.jdk8.stream2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        Student zhangsan = new Student("zhangsan", 80);
        Student lisi = new Student("lisi", 90);
        Student wangwu = new Student("wangwu", 100);
        Student zhaoliu = new Student("zhaoliu", 90);
        Student zhaoliu2 = new Student("zhaoliu", 90);

        List<Student> students = Arrays.asList(zhangsan, lisi, wangwu, zhaoliu, zhaoliu2);

        students.stream()
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("===============");

        System.out.println(students.stream()
                                   .collect(Collectors.counting()));
        System.out.println(students.stream()
                                   .count());

        System.out.println("----------");

        students.stream()
                .collect(Collectors.minBy(Comparator.comparingInt(Student::getScore)))
                .ifPresent(System.out::println);
        students.stream()
                .collect(Collectors.maxBy(Comparator.comparingInt(Student::getScore)))
                .ifPresent(System.out::println);
        System.out.println(students.stream()
                                   .collect(Collectors.averagingInt(Student::getScore)));
        System.out.println(students.stream()
                                   .collect(Collectors.summingInt(Student::getScore)));
        System.out.println(students.stream()
                                   .collect(Collectors.summarizingInt(Student::getScore)));
        System.out.println("========");

        System.out.println(students.stream()
                                   .map(Student::getName)
                                   .collect(Collectors.joining()));
        System.out.println(students.stream()
                                   .map(Student::getName)
                                   .collect(Collectors.joining(", ")));
        System.out.println(students.stream()
                                   .map(Student::getName)
                                   .collect(Collectors.joining(", ", "<begin> ", " <end>")));
        System.out.println("---------------------------");

        Map<Integer, Map<String, List<Student>>> map =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getScore,
                                                       Collectors.groupingBy(Student::getName)));
        System.out.println(map);

        Map<Boolean, List<Student>> map2 = students.stream()
                                                   .collect(Collectors.partitioningBy(
                                                           student -> student.getScore() > 80));
        System.out.println(map2);

        Map<Boolean, Map<Boolean, List<Student>>> map3 =
                students.stream()
                        .collect(Collectors.partitioningBy(student -> student.getScore() > 80,
                                                           Collectors.partitioningBy(
                                                                   student -> student.getScore() > 90)));
        System.out.println(map3);
        System.out.println("==============================");

        Map<Boolean, Long> map4 =
                students.stream()
                        .collect(Collectors.partitioningBy(student -> student.getScore() > 80,
                                                           Collectors.counting()));
        System.out.println(map4);

        Map<String, Student> map5 =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getName,
                                Collectors.collectingAndThen(Collectors.minBy(
                                        Comparator.comparingInt(Student::getScore)),
                                                             Optional::get)));
        System.out.println(map5);
    }
}
