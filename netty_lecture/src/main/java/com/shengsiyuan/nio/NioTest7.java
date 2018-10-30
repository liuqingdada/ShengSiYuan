package com.shengsiyuan.nio;

import net.vidageek.mirror.dsl.Mirror;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class NioTest7 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.flip();
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        System.out.println(buffer.isReadOnly());
        System.out.println(readOnlyBuffer.isReadOnly());

        byte[] hb = (byte[]) new Mirror().on(buffer)
                                         .get()
                                         .field("hb");
        System.out.println(Arrays.toString(hb));

        hb[9] = 100;

        byte[] hb2 = (byte[]) new Mirror().on(readOnlyBuffer)
                                          .get()
                                          .field("hb");
        hb2[9] = 127;
        System.out.println(Arrays.toString(hb2));
    }
}
