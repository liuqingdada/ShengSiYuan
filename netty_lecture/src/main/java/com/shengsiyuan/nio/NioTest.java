package com.shengsiyuan.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;
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
    }
}
