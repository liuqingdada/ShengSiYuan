package com.shengsiyuan.decorator;

import java.util.Arrays;

public class Client {

    public static void main(String[] args) {
        Component component = new ConcreteDecorator2(
                new ConcreteDecorator(new ConcreteComponent()));

        byte[] bytes = new byte[1024];

        int read = component.read(bytes);
        System.out.println(read);
        System.out.println(Arrays.toString(bytes));
        System.out.println(new String(bytes));

        component.close();
    }
}
