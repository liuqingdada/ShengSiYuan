package com.shengsiyuan.nio;

import com.java.util.function.UncheckedConsumer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class NioServer {

    private Map<String, SocketChannel> clients = new HashMap<>();

    private void run(int port) throws IOException {
        clients.clear();
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("listen on: " + port);

        //noinspection InfiniteLoopStatement
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.forEach(UncheckedConsumer.unchecked(selectionKey -> {
                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel client = server.accept();

                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);

                    clients.put(UUID.randomUUID()
                                    .toString(), client);

                } else if (selectionKey.isReadable()) {
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int read = client.read(readBuffer);

                    AtomicReference<String> senderKey = new AtomicReference<>("");
                    clients.entrySet()
                           .stream()
                           .filter(entry -> entry.getValue() == client)
                           .findFirst()
                           .ifPresent(entry -> senderKey.set(entry.getKey()));

                    if (read > 0) {
                        readBuffer.flip();
                        //Charset charset = Charset.forName("utf-8");
                        //String msg = String.valueOf(charset.decode(readBuffer)
                        //                                   .array());
                        String msg = new String(readBuffer.array(), 0, read);

                        System.out.print(client + ": " + msg);

                        clients.entrySet()
                               .stream()
                               .filter(entry -> entry.getValue() != client)
                               .forEach(UncheckedConsumer.unchecked(entry -> {
                                   SocketChannel socketChannel = entry.getValue();
                                   ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                   writeBuffer.put((senderKey.get() + ": " + msg).getBytes());
                                   writeBuffer.flip();

                                   socketChannel.write(writeBuffer);
                               }));

                    }

                    if (read == -1) {
                        client.close();
                        clients.remove(senderKey.get());
                        System.out.println(client + " offline");
                    }
                }
            }));

            selectionKeys.clear();
        }
    }

    public static void main(String[] args) {
        int port = 8899;

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            new NioServer().run(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
