package com.shengsiyuan.netty.thirdexample.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class ChatClient {

    private void connect() throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                     .channel(NioSocketChannel.class)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             ch.pipeline()
                               .addLast(new DelimiterBasedFrameDecoder(4096,
                                                                       Delimiters.lineDelimiter()))
                               .addLast(new StringDecoder(CharsetUtil.UTF_8))
                               .addLast(new StringEncoder(CharsetUtil.UTF_8))
                               .addLast(new ChatClientHandler());
                         }
                     });

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8899)
                                                   .sync();
            Channel channel = channelFuture.channel();
            ////////////

            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                //noinspection InfiniteLoopStatement
                for (; ; ) {
                    channel.writeAndFlush(br.readLine() + "\r\n");
                }
            }

            //channelFuture.channel()
            //             .closeFuture()
            //             .sync();

        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatClient().connect();
    }
}
