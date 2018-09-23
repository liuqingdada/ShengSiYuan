package com.shengsiyuan;

import java.util.Arrays;
import java.util.List;

public class For {

    private void forTest() throws RuntimeException {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
        list.forEach(item -> {

            System.out.print("item = ");
            System.out.println(item);

            throw new RuntimeException("exception innner");
        });
    }

    public static void main(String[] args) {
        For f = new For();
        try {
            f.forTest();

        } catch (Exception e) {
            System.out.println("main --- all in control ... ");
        }


        System.out.println("main end ...");
    }
}
