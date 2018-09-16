package com.shengsiyuan.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class PersopnTest {

    public static void main(String[] args) {
        PersopnTest test = new PersopnTest();

        Person person1 = new Person("zhangsan", 20);
        Person person2 = new Person("lisi", 30);
        Person person3 = new Person("wangwu", 40);

        List<Person> persons = Arrays.asList(person1, person2, person3);

        List<Person> ps = test.getPersonsByUsername("zhangsan", persons);
        ps.forEach(person -> System.out.println(person.toString()));

        System.out.println("--------------");

        ps = test.getPersonsByAge(30, persons);
        ps.forEach(System.out::println);
    }

    private List<Person> getPersonsByUsername(String username, List<Person> persons) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(persons);
        return persons.stream()
                      .filter(f -> username.equals(f.getUsername()))
                      .collect(Collectors.toList());
    }

    private List<Person> getPersonsByAge(int age, List<Person> persons) {
        Objects.requireNonNull(persons);

        BiFunction<Integer, List<Person>, List<Person>> biFunction = (a, ps) ->
                ps.stream()
                  .filter(f -> f.getAge() > age)
                  .collect(Collectors.toList());

        return biFunction.apply(age, persons);
    }
}
