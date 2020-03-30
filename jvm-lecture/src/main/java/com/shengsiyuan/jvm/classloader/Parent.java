package com.shengsiyuan.jvm.classloader;

class Parent {
    public static final String str = "hello world";

    public static final short s = 7;

    public static final int i = 128;

    public static final int m = -1;

    static {
        System.out.println("Parent static block");
    }
}