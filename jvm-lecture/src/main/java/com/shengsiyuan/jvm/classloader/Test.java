package com.shengsiyuan.jvm.classloader;

/**
 * Created by andy
 * 2020/3/26.
 * Email: 1239604859@qq.com
 * <p>
 * 对于静态字段来说, 只有直接定义了该字段的类草会被初始化
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Child.str); // 对 Parent 的主动使用

        System.out.println(Child.str2); // 对 Child 的主动使用
    }
}

class Parent {
    public static String str = "hello world";

    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent {
    public static String str2 = "welcome";

    static {
        System.out.println("Child static block");
    }
}
