package com.shengsiyuan.netty.webwocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebSocketClient {

    private static class TextHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg)
                throws Exception {
            System.out.println("receive msg: " + msg.text());
        }
    }

    public static void main(String[] args) throws Exception {
        URI uri = new URI("ws://127.0.0.1:8899/ws");

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                     .channel(NioSocketChannel.class)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                             ch.pipeline()
                               .addLast(new HttpClientCodec())
                               .addLast(new ChunkedWriteHandler())
                               .addLast(new HttpObjectAggregator(8192))
                               // ws://server:port/context_path
                               .addLast(new WebSocketClientProtocolHandler(uri,
                                                                           WebSocketVersion.V13,
                                                                           null,
                                                                           false,
                                                                           new DefaultHttpHeaders(),
                                                                           8192))
                               .addLast(new TextHandler());
                         }
                     });

            ChannelFuture channelFuture = bootstrap.connect(uri.getHost(), uri.getPort())
                                                   .sync();
            Channel channel = channelFuture.channel();
            ////////////

            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
                //noinspection InfiniteLoopStatement
                for (; ; ) {
                    String line = br.readLine();
                    // close
                    // new CloseWebSocketFrame()
                    // ping
                    // new PingWebSocketFrame(Unpooled.wrappedBuffer(new byte[] { 8, 1, 8, 1 }))
                    WebSocketFrame frame = new TextWebSocketFrame(line);
                    channel.writeAndFlush(frame);
                }
            }

        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
