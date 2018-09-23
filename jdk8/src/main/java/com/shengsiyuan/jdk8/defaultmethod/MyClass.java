package com.shengsiyuan.jdk8.defaultmethod;

import java.util.Arrays;

public class MyClass extends MyInterfaceImpl implements MyInterface, MyInterface2 {

    protected MyClass() {
        System.out.println(super.getClass()
                                .getName());
        System.out.println(getClass().getName());
    }

//    @Override
//    public void myMethod() {
//        MyInterface.super.myMethod();
//        MyInterface2.super.myMethod();
//    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();

        myClass.print();
        Arrays.stream(myClass.getClass().getDeclaredMethods())
              .forEach(item -> System.out.println(item.getName()));
    }
}
