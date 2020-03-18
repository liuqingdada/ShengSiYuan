package com.shengsiyuan.netty.bytebuf;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * Created by andy
 * 2020/3/18.
 * Email: 1239604859@qq.com
 */

public class AtomicUpdaterTest {
    public static void main(String[] args) {
//        test();
        updaterTest();
    }

    private static void test() {
        Person person = new Person();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(person.age++);
            }).start();
        }
    }

    private static void updaterTest() {
        Person person = new Person();

        AtomicIntegerFieldUpdater<Person> updater =
                AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(updater.getAndIncrement(person));
            }).start();
        }
    }

    private static class Person {
        volatile int age = 0;
    }
}
