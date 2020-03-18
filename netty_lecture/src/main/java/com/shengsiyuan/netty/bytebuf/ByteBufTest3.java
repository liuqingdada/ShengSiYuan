package com.shengsiyuan.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Created by andy
 * 2020/3/18.
 * Email: 1239604859@qq.com
 * <p>
 * Composite buffer
 */

public class ByteBufTest3 {
    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();
        ByteBuf heapBuf = Unpooled.buffer(10);
        ByteBuf directBuf = Unpooled.directBuffer(8);
        compositeByteBuf.addComponents(heapBuf, directBuf);

        compositeByteBuf.forEach(System.out::println);

        System.out.println(compositeByteBuf.hasArray());
        System.out.println(compositeByteBuf.isDirect());
        System.out.println(compositeByteBuf.readerIndex());
        System.out.println(compositeByteBuf.writerIndex());
        System.out.println(compositeByteBuf.capacity());
    }
}
