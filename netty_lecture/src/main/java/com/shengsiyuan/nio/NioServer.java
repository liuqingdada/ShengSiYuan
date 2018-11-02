package com.shengsiyuan.nio;

import com.java.lang.UncheckedRunnable;
import com.java.util.function.UncheckedConsumer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class NioServer {

    private Map<String, SocketChannel> clients = new ConcurrentHashMap<>();

    private ExecutorService readService = Executors.newFixedThreadPool(4);

    @SuppressWarnings("InfiniteLoopStatement")
    private void run(int port) throws IOException {
        clients.clear();
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("listen on: " + port);

        try {
            ExecutorService inputService = Executors.newSingleThreadExecutor();
            inputService.execute(UncheckedRunnable.unchecked(() -> {
                while (true) {
                    InputStreamReader isr = new InputStreamReader(System.in);
                    BufferedReader br = new BufferedReader(isr);
                    String msg = br.readLine() + "\n";
                    sendMessage(msg);
                }
            }));

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.stream()
                             .filter(SelectionKey::isValid)
                             .forEach(UncheckedConsumer.unchecked(selectionKey -> {
                                 if (selectionKey.isAcceptable()) {
                                     ServerSocketChannel server =
                                             (ServerSocketChannel) selectionKey.channel();
                                     SocketChannel client = server.accept();

                                     client.configureBlocking(false);
                                     client.register(selector, SelectionKey.OP_READ);

                                     clients.put(UUID.randomUUID()
                                                     .toString(), client);

                                 } else if (selectionKey.isReadable()) {
                                     readService.execute(new ReadRunnable(selectionKey));
                                 }
                             }));
                selectionKeys.clear();
            }
        } catch (Exception e) {
            loge("NioServer.run()", e);
        }
    }

    private void sendMessage(String msg) {
        try {
            clients.entrySet()
                   .forEach(UncheckedConsumer.unchecked(entry -> {
                       SocketChannel socketChannel = entry.getValue();
                       ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                       writeBuffer.put(("server: " + msg).getBytes());
                       writeBuffer.flip();

                       socketChannel.write(writeBuffer);
                   }));
        } catch (Exception e) {
            loge("NioServer.sendMessage", e);
        }
    }

    private void sendMessage2AllClient(SocketChannel client,
                                       AtomicReference<String> senderKey,
                                       String msg) {
        try {
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
        } catch (Exception e) {
            loge("NioServer.sendMessage2AllClient()", e);
        }
    }

    private synchronized void clientOffline(SocketChannel client,
                                            AtomicReference<String> senderKey) {
        try {
            //client.shutdownInput();
            //client.shutdownOutput();
            client.close();
        } catch (IOException e) {
            loge("NioServer.clientOffline()", e);
        }

        if (clients.containsKey(senderKey.get())) {
            clients.remove(senderKey.get());
            String msg = client + ", " + senderKey.get() + ", offline\n";
            System.out.print(msg);

            sendMessage2AllClient(client, senderKey, msg);
            System.out.println("clients size: " + clients.size());
        }
    }

    private class ReadRunnable implements Runnable {
        private SelectionKey selectionKey;

        ReadRunnable(SelectionKey selectionKey) {
            this.selectionKey = selectionKey;
        }

        @Override
        public void run() {
            SocketChannel client = (SocketChannel) selectionKey.channel();

            AtomicReference<String> senderKey = new AtomicReference<>("");
            clients.entrySet()
                   .stream()
                   .filter(entry -> entry.getValue() == client)
                   .findFirst()
                   .ifPresent(entry -> senderKey.set(entry.getKey()));

            ByteBuffer readBuffer = ByteBuffer.allocate(1024);

            int read = 0;

            try {
                if (client.isOpen() && client.isConnected()) {
                    read = client.read(readBuffer);
                }
            } catch (IOException e) {
                loge("ReadRunnable.run()", e);
            }

            if (read == -1) {
                clientOffline(client, senderKey);
            }

            if (read > 0) {
                readBuffer.flip();
                //Charset charset = Charset.forName("utf-8");
                //String msg = String.valueOf(charset.decode(readBuffer)
                //                                   .array());
                String msg = new String(readBuffer.array(), 0, read);

                System.out.print(client + ": " + msg);

                sendMessage2AllClient(client, senderKey, msg);
            }
        }
    }

    private void loge(String method, Exception e) {
        System.out.print("*************\n" + method + "\n" + e.getMessage() + "\n*************\n");
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
