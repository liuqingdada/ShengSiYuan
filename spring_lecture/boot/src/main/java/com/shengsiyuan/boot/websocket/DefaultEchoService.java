package com.shengsiyuan.boot.websocket;

/**
 * Created by andy
 * 2020/1/15.
 * Email: 1239604859@qq.com
 */

public class DefaultEchoService implements EchoService {

    private final String echoFormat;

    public DefaultEchoService(String echoFormat) {
        this.echoFormat = echoFormat;
    }

    @Override
    public String getMeddage(String message) {
        return String.format(this.echoFormat, message);
    }
}
