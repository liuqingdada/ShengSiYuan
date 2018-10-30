package com.shengsiyuan.nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

public class NioTest10 {

    public static void main(String[] args) throws Exception {

        RandomAccessFile raf = new RandomAccessFile("test_file/NioTest10.txt", "rw");

        FileChannel fileChannel = raf.getChannel();

        FileLock lock = fileChannel.lock();

        System.out.println(lock.isValid());
        System.out.println(lock.isShared());
        lock.release();

        fileChannel.close();
        raf.close();
    }
}
