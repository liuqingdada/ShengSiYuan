package com.shengsiyuan.nio;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Stream;

public class NioTest {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);
        Stream.generate(() -> {
            SecureRandom sr = new SecureRandom();
            return sr.nextInt(20);
        })
                .limit(intBuffer.capacity())
                .forEach(intBuffer::put); // write

        intBuffer.flip(); // 状态反转

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get()); // read
        }

        intBuffer.flip();
        System.out.println("=============");

        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get()); // read
        }

        System.out.println("=================");

        byte[] ba = {1, 2, 3, 4};
        // 4 bytes to int
        int in = ByteBuffer.wrap(ba).order(ByteOrder.LITTLE_ENDIAN).getInt();
        System.out.println(in);


        byte[] ab = null;
        // int to 4 bytes method 1
        ab = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(in).array();
        System.out.println(Arrays.toString(ab));

        // int to 4 bytes method 2
        ab = new byte[4];
        ByteBuffer.wrap(ab).order(ByteOrder.LITTLE_ENDIAN).asIntBuffer().put(in);
        System.out.println(Arrays.toString(ab));
    }
}
