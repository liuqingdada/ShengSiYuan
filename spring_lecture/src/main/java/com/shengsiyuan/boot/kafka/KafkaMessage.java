package com.shengsiyuan.boot.kafka;

import java.util.Date;

/**
 * Created by andy
 * 2020/1/20.
 * Email: 1239604859@qq.com
 */

public class KafkaMessage {

    private long id;

    private String username;

    private String password;

    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
