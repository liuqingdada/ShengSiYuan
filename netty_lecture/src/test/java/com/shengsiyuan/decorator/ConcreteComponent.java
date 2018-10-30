package com.shengsiyuan.decorator;

import java.util.Random;

public class ConcreteComponent implements Component {
    @Override
    public int read(byte[] bytes) {
        System.out.println("read...");

        for (int i = 0; i < bytes.length; i++) {
            Random random = new Random();
            bytes[i] = (byte) random.nextInt(256);
        }

        return bytes.length;
    }

    @Override
    public void close() {
        System.out.println("close");
    }
}
