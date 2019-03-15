package com.shengsiyuan.nio

import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Created by andy
 * 2019-03-15.
 * Email: 1239604859@qq.com
 */

fun main() {
    val byteChannel = Files.newByteChannel(Paths.get("../netty_lecture/test_file/NioTest2.txt"))
    var readLength: Int
    val buff = ByteBuffer.allocate(1024)
    while (true) {
        buff.clear()
        readLength = byteChannel.read(buff)
        if (-1 == readLength) {
            break
        }
        buff.flip()
        println(String(buff.array(), 0, buff.limit()))
    }


    val fis = FileInputStream("../netty_lecture/test_file/NioTest2.txt")
    val fileChannel = fis.channel

    val byteBuffer = ByteBuffer.allocate(8 * 1024)

    while (true) {
        byteBuffer.clear()
        val read = fileChannel.read(byteBuffer)
        if (-1 == read) {
            break
        }

        byteBuffer.flip()
        println(String(byteBuffer.array(), 0, byteBuffer.limit()))
    }

    fileChannel.close()
    fis.close()
}