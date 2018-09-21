package com.shengsiyuan.jdk8.methodreference;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MethodReferenceTest {

    public static void main(String[] args) {

        Student student = new Student("zhangsan", 10);
        Student student2 = new Student("lisi", 90);
        Student student3 = new Student("wangwu", 50);
        Student student4 = new Student("zhaoliu", 40);

        List<Student> students = Arrays.asList(student, student2, student3, student4);

//        students.sort((s1, s2) -> Student.compareStuentByScore(s1, s2));
//        students.forEach(item -> System.out.println(item.getScore()));

//        students.sort(Student::compareStuentByScore);
//        students.forEach(item -> System.out.println(item.getScore()));

//        students.sort(Student::compareStudnetByName);
//        students.forEach(item -> System.out.println(item.getName()));

//        StudentComparator studentComparator = new StudentComparator();
//        students.sort(studentComparator::compareStudentByName);
//        students.forEach(item -> System.out.println(item.getName()));

        students.sort((s1, s2) -> s1.compareByName(s2));
        students.sort(Student::compareByName); // 第一个参数是调用此函数的引用

        students.sort((s1, s2) -> s2.compareByName(s1)); // 问题的关键在于是谁去调用, 这样就没法用方法引用了


        List<String> cities = Arrays.asList("qingdao", "chongqing", "tianjin", "beijing",
                                            "chengdu");
//        Collections.sort(cities, (c1, c2) -> c1.compareToIgnoreCase(c2));
//        System.out.println(cities);
        Collections.sort(cities, String::compareToIgnoreCase);
        cities.forEach(System.out::println);
    }
}
