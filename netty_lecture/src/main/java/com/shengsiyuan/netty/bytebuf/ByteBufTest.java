package com.shengsiyuan.netty.bytebuf;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by andy
 * 2020/3/16.
 * Email: 1239604859@qq.com
 */

public class ByteBufTest {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.buffer(10);
        int writableBytes = byteBuf.writableBytes();
        byteBuf.maxCapacity();
        byteBuf.maxWritableBytes();
        for (int i = 0; i < writableBytes; i++) {
            byteBuf.writeByte(i);
        }

        for (int i = 0; i < byteBuf.readableBytes(); i++) {
            byte b = byteBuf.getByte(i);
            System.out.println(b);
        }

        while (byteBuf.readableBytes() > 0) {
            System.out.println(byteBuf.readByte());
        }
        System.out.println("===========");

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(new ByteBuffer[]{byteBuffer});

        byteBuffer.put((byte) 0x10);
        while (wrappedBuffer.readableBytes() > 0) {
            System.out.println(wrappedBuffer.readByte());
        }
    }
}
