package com.shengsiyuan.netty.bytebuf;

import java.nio.charset.StandardCharsets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by andy
 * 2020/3/18.
 * Email: 1239604859@qq.com
 */

public class ByteBufTest2 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("杨hello world", StandardCharsets.UTF_8);
        if (byteBuf.hasArray()) {
            byte[] content = byteBuf.array();
            System.out.println(new String(content, StandardCharsets.UTF_8));
            System.out.println(byteBuf.toString(StandardCharsets.UTF_8));
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
        }
        int cap = 1000000000;
        ByteBuf buffer = Unpooled.buffer(cap);
        ByteBuf buffer2 = Unpooled.directBuffer(cap);

        System.out.println(byteBuf.getCharSequence(0, 4, StandardCharsets.UTF_8));
        System.out.println(byteBuf.getCharSequence(4, 6, StandardCharsets.UTF_8));
    }
}
