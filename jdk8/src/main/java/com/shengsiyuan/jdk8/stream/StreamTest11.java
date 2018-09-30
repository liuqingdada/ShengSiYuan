package com.shengsiyuan.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class StreamTest11 {

    public static void main(String[] args) {
        Student student = new Student("zhangsan", 100, 20);
        Student student2 = new Student("lisi", 90, 20);
        Student student3 = new Student("wangwu", 90, 30);
        Student student4 = new Student("zhangsan", 80, 40);

        List<Student> students = Arrays.asList(student, student2, student3, student4);

        Map<String, List<Student>> map =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getName));
        System.out.println(map + "\n\n");


        Map<Integer, List<Student>> map2 =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getScore));
        System.out.println(map2 + "\n\n");

        Map<String, Long> map3 =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getName, Collectors.counting()));
        System.out.println(map3 + "\n\n");

        Map<String, Double> map4 =
                students.stream()
                        .collect(Collectors.groupingBy(Student::getName,
                                                       Collectors.averagingDouble(
                                                               Student::getScore)));
        System.out.println(map4 + "\n\n");

        ConcurrentHashMap<String, Double> concurrentHashMap =
                students.stream()
                        .collect(Collectors.groupingBy(
                                Student::getName,
                                ConcurrentHashMap::new,
                                Collectors.averagingDouble(Student::getScore)));
        System.out.println(concurrentHashMap + "\n\n");

        Map<Boolean, List<Student>> map5 =
                students.stream()
                        .collect(Collectors.partitioningBy(s -> s.getScore() >= 90));
        System.out.println(map5 + "\n\n");
    }
}
