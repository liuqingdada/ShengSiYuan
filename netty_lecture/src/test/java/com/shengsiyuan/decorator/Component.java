package com.shengsiyuan.decorator;

public interface Component {

    int read(byte[] bytes);

    void close();
}
