package com.shengsiyuan;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suhen
 * 18-11-26.
 * Email: 1239604859@qq.com
 */

class Generic {

    public static void main(String[] args) {
        List<?> list = new ArrayList<>();

        List<String> strs = new ArrayList<>();

        List<? super String> strs2 = new ArrayList<>();

        //list.addAll(strs);

        list = strs;

        strs2 = strs;
    }
}
