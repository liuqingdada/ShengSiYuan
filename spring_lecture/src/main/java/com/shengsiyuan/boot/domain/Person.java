package com.shengsiyuan.boot.domain;

import java.util.Date;

/**
 * Created by andy
 * 2020/1/14.
 * Email: 1239604859@qq.com
 */

public class Person {

    private int id;

    private String name;

    private Date birth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }
}
