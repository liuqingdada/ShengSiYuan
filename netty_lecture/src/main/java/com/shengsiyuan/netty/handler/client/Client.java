package com.shengsiyuan.netty.handler.client;

import com.shengsiyuan.netty.handler.decoder.ByteToLongDecoder;
import com.shengsiyuan.netty.handler.encoder.LongToByteEncoder;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created by andy
 * 2020/3/20.
 * Email: 1239604859@qq.com
 */

public class Client {
    public static void main(String[] args) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new Handler());

            ChannelFuture connectFuture = bootstrap.connect("localhost", 8899).sync();
            connectFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    private static class Handler extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ch.pipeline()
                    .addLast(new LoggingHandler(LogLevel.INFO))
                    .addLast(new ByteToLongDecoder())
                    .addLast(new LongToByteEncoder())
                    .addLast(new ClientInboundHandler());
        }
    }
}
