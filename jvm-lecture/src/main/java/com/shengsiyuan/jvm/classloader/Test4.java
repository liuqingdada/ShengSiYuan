package com.shengsiyuan.jvm.classloader;

/**
 * Created by andy
 * 2020/3/30.
 * Email: 1239604859@qq.com
 * <p>
 * 对于数组实例来说, 其类型是由JVM在运行期动态生成的, 其父类型就是Object
 * <p>
 * 对于数组来说, JavaDoc经常将构成数组的元素为Component, 实际上就是讲数组降低一个维度后的类型
 * <p>
 * <p>
 * <p>
 * 助记符:
 * anewarray <TYPE>: 标识创建一个引用类型的(如类, 接口, 数组)数组, 并将其引用值压入栈顶
 * newarray  <TYPE>: 表示创建一个指定的原生类型(如int, float, char等)的数组, 并将其引用值压入栈顶
 * multianewarray <TYPE>,  <维度>:
 */

public class Test4 {
    public static void main(String[] args) {
        Parent parent = new Parent();
        System.out.println("----------");

        Parent[] parents = new Parent[1];
        Parent[][][] parents2 = new Parent[1][1][1];
        System.out.println(parents.getClass());
        System.out.println(parents.getClass().getSuperclass());
        System.out.println(parents2.getClass());
        System.out.println(parents2.getClass().getSuperclass());

        System.out.println(parent.getClass());

        System.out.println("------------");

        int[][][] ints = new int[1][1][1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
    }

    private static class Parent {
        static {
            System.out.println("Parent static block");
        }
    }
}
