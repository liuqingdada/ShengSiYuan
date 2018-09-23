package com.shengsiyuan.jdk8.defaultmethod;

public class MyInterfaceImpl implements MyInterface {

    protected MyInterfaceImpl() {
        System.out.println(this);
        System.out.println(getClass().getName());
    }

    @Override
    public void myMethod() {
        System.out.println(getClass().getSimpleName());
    }

    protected void print() {
        System.out.println(getClass().getSimpleName());
    }
}
