package com.shengsiyuan.nio;

import java.nio.ByteBuffer;

public class NioTest5 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put((byte) 0xFF);
        buffer.putChar('我');
        buffer.putShort((short) 0xFF);
        buffer.putInt(1024);
        buffer.putLong(1024102410241024L);
        buffer.putFloat(3.14F);
        buffer.putDouble(3.14159265332258582842835823858285832582855275888184818428523962909080);

        buffer.flip();

        byte b = buffer.get();
        char aChar = buffer.getChar();
        short aShort = buffer.getShort();
        int anInt = buffer.getInt();
        long aLong = buffer.getLong();
        float aFloat = buffer.getFloat();
        double aDouble = buffer.getDouble();

        System.out.println(b);
        System.out.println(aChar);
        System.out.println(aShort);
        System.out.println(anInt);
        System.out.println(aLong);
        System.out.println(aFloat);
        System.out.println(aDouble);
    }
}
