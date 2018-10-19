package com.shengsiyuan.netty.rpc.protobuf;

import com.shengsiyuan.protobuf.DataInfo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class ProtoServer {

    private static class ProtoServerHandler
            extends SimpleChannelInboundHandler<DataInfo.ProtoMessage> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, DataInfo.ProtoMessage msg)
                throws Exception {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) throws Exception {

        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup, workerGroup)
                     .channel(NioServerSocketChannel.class)
                     .handler(new LoggingHandler(LogLevel.INFO)) // for boosGroup
                     .childHandler(new ChannelInitializer<SocketChannel>() { // for workerGroup
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             ch.pipeline()
                               .addLast(new ProtobufVarint32FrameDecoder())
                               .addLast(new ProtobufDecoder(
                                       DataInfo.ProtoMessage.getDefaultInstance()))
                               .addLast(new ProtobufVarint32LengthFieldPrepender())
                               .addLast(new ProtobufEncoder())
                               .addLast(new ProtoServerHandler());
                         }
                     });
            ChannelFuture channelFuture = bootstrap.bind(8899)
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
