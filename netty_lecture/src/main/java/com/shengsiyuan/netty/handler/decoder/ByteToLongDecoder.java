package com.shengsiyuan.netty.handler.decoder;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * Created by andy
 * 2020/3/20.
 * Email: 1239604859@qq.com
 */

public class ByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(
            ChannelHandlerContext ctx,
            ByteBuf in,
            List<Object> out
    ) throws Exception {
        System.out.println("decode invoked.");
        System.out.println(in.readableBytes());

        if (in.readableBytes() >= 8) {
            out.add(in.readLong());
        }
    }
}
