package com.shengsiyuan.jdk8;

public class Test2 {

    @FunctionalInterface
    private interface MyInterface {
        void test();

        String toString();
    }

    private void print(MyInterface myInterface) {
        System.out.println("1");

        myInterface.test();

        System.out.println("2");
    }

    public static void main(String[] args) {
        Test2 test = new Test2();

        test.print(new MyInterface() {
            @Override
            public void test() {
                System.out.println("test");
            }
        });

        System.out.println("=================");

        test.print(() -> {
            System.out.println("test2");
        });

        System.out.println("================");

        MyInterface myInterface = () -> {
            System.out.println("test3");
        };
        test.print(myInterface);

        System.out.println("=================");

        System.out.println(myInterface.getClass());
        System.out.println(myInterface.getClass().getSuperclass());
        System.out.println(myInterface.getClass().getInterfaces()[0]);
    }

}
