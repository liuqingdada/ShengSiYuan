package com.shengsiyuan.nio;

import com.java.util.function.UncheckedConsumer;
import com.java.util.function.UncheckedIntConsumer;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

public class NioTest12 {

    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];

        Stream.iterate(0, (i -> ++i))
              .limit(ports.length)
              .forEach(i -> ports[i] = i + 5000);

        System.out.println(Arrays.toString(ports));


        Selector selector = Selector.open();

        Arrays.stream(ports)
              .forEach(UncheckedIntConsumer.unchecked(port -> {
                  ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

                  serverSocketChannel.configureBlocking(false);

                  ServerSocket serverSocket = serverSocketChannel.socket();
                  serverSocket.bind(new InetSocketAddress(port));

                  serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                  System.out.println("监听端口: " + port);
              }));

        while (true) {
            int keyNums = selector.select();
            System.out.println("key set size: " + keyNums);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println(selectionKeys);

            selectionKeys.forEach(UncheckedConsumer.unchecked(selectionKey -> {
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel selectableChannel =
                            (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = selectableChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    selectionKeys.remove(selectionKey);
                    System.out.println("get client connection: " + socketChannel);

                } else if (selectionKey.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int bytesRead = 0;

                    while (true) {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);

                        if (read <= 0) {
                            break;
                        }

                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);

                        bytesRead += read;
                    }

                    System.out.println("read: " + bytesRead);

                    selectionKeys.remove(selectionKey);
                }
            }));
        }
    }
}
