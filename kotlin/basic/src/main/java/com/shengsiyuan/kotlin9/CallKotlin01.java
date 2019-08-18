package com.shengsiyuan.kotlin9;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangliuqing
 * 2019-03-12.
 * Email: 1239604859@qq.com
 */
class CallKotlin01 {

    public static void main(String[] args) {
        List<String> strings = GenericWipe01Kt.aList(new ArrayList<>());
        System.out.println(strings);

        List<Integer> integers = GenericWipe01Kt.aList2(new ArrayList<>());
        System.out.println(integers);
    }
}
