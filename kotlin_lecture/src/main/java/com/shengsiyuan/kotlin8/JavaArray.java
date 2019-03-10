package com.shengsiyuan.kotlin8;

/**
 * Created by yangliuqing
 * 2019-03-10.
 * Email: 1239604859@qq.com
 */
class JavaArray {

    public static void main(String[] args) {
        Object[] objects = new String[2]; // 协变
        objects[0] = "hello";
        objects[1] = 1; // ArrayStoreException

        System.out.println(objects[0]);
        System.out.println(objects[1]);
    }
}
