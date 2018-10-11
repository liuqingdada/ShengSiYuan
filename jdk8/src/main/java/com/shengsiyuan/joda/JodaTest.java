package com.shengsiyuan.joda;

import org.joda.primitives.list.IntList;
import org.joda.primitives.list.impl.ArrayIntList;

public class JodaTest {

    public static void main(String[] args) {
        IntList list = new ArrayIntList();
        list.add(1);
        list.add(2);
        list.forEach(System.out::println);
    }
}
