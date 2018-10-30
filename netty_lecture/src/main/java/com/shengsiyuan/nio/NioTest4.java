package com.shengsiyuan.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest4 {

    public static void main(String[] args) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(128);

        FileInputStream fis = new FileInputStream("netty_lesson.txt");
        FileOutputStream fos = new FileOutputStream("test_file/NioTest4.txt");

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        while (true) {
            buffer.clear();
            int read = fisChannel.read(buffer);
            System.out.println(read);
            if (-1 == read) {
                break;
            }

            buffer.flip();

            fosChannel.write(buffer);
        }

        fisChannel.close();
        fosChannel.close();
        fis.close();
        fos.close();
    }
}
