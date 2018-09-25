package com.shengsiyuan.netty.secondexample.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class MyServer {

    public static void main(String[] args) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup)
                           .channel(NioServerSocketChannel.class)
                           .handler(new LoggingHandler(LogLevel.DEBUG)) // for boosGroup
                           .childHandler(new MyServerInitializer()); // for workerGroup
            ChannelFuture channelFuture = serverBootstrap.bind(8899)
                                                         .sync();
            channelFuture.channel()
                         .closeFuture()
                         .sync();

        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
