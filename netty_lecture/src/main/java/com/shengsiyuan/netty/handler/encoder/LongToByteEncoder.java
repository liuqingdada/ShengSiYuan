package com.shengsiyuan.netty.handler.encoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by andy
 * 2020/3/24.
 * Email: 1239604859@qq.com
 */

public class LongToByteEncoder extends MessageToByteEncoder<Long> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("encode invoked.");
        System.out.println(msg);
        out.writeLong(msg);
    }
}
