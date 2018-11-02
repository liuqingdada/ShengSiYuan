package com.shengsiyuan.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class OldServer {
    private void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8899);

        //noinspection InfiniteLoopStatement
        while (true) {
            Socket client = serverSocket.accept();

            try {
                DataInputStream dis = new DataInputStream(client.getInputStream());
                byte[] readBytes = new byte[4096];

                while (true) {
                    int read = dis.read(readBytes, 0, readBytes.length);

                    if (-1 == read) {
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new OldServer().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
