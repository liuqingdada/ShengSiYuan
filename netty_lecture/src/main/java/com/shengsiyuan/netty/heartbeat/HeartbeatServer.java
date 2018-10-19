package com.shengsiyuan.netty.heartbeat;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartbeatServer {

    private static class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
            if (evt instanceof IdleStateEvent) {
                IdleStateEvent event = (IdleStateEvent) evt;

                String eventType = "";

                switch (event.state()) {
                    case READER_IDLE:
                        eventType = "read idle";
                        break;

                    case WRITER_IDLE:
                        eventType = "write idle";
                        break;

                    case ALL_IDLE:
                        eventType = "read write all adle";
                        break;
                }

                System.out.println(ctx.channel()
                                      .remoteAddress() + ": " + eventType);
                ctx.channel()
                   .close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup, workerGroup)
                           .channel(NioServerSocketChannel.class)
                           .handler(new LoggingHandler(LogLevel.INFO)) // for boosGroup
                           .childHandler(new ChannelInitializer<SocketChannel>() {
                               @Override
                               protected void initChannel(SocketChannel ch) throws Exception {
                                   ch.pipeline()
                                     .addLast(new IdleStateHandler(5,
                                                                   7,
                                                                   10,
                                                                   TimeUnit.SECONDS))
                                     .addLast(new HeartbeatServerHandler());
                               }
                           }); // for workerGroup
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
