package com.shengsiyuan.boot.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by andy
 * 2020/1/14.
 * Email: 1239604859@qq.com
 */

public class AppConfigBean {
    @Value("${suhenConfig.suhenObject.name}")
    private String name;
    @Value("${suhenConfig.suhenObject.age}")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
