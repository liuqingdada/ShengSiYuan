package com.shengsiyuan.kotlin9;

import java.io.IOException;

/**
 * Created by yangliuqing
 * 2019-03-12.
 * Email: 1239604859@qq.com
 */
class CallKotlin04 {

    public static void main(String[] args) {
        Email email = new Email();
        try {
            email.method(1);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("main end");
    }
}
