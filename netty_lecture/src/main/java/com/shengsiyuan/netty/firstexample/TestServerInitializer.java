package com.shengsiyuan.netty.firstexample;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        pipeline.addLast(HttpServerCodec.class.getSimpleName(), new HttpServerCodec())
                .addLast(TestHttpServerHandler.class.getSimpleName(), new TestHttpServerHandler());

    }
}
