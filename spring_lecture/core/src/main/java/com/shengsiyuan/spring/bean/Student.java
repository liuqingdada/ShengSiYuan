package com.shengsiyuan.spring.bean;

/**
 * Created by liuqing.yang
 * 2020/5/9.
 * Email: 1239604859@qq.com
 * <p>
 * POJO: Plain Old Java Object
 * 这种说法来自之前的 EZB
 */
public class Student {
    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
