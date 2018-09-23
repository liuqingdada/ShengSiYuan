package com.shengsiyuan.jdk8.defaultmethod;

public interface MyInterface {

    default void myMethod() {
        System.out.println("MyInterface -> " + getClass().getName());
    }
}
