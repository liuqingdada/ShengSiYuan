package com.shengsiyuan.nio

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.SelectionKey
import java.nio.channels.Selector
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.*

/**
 * Created by yangliuqing
 * 2019-03-16.
 * Email: 1239604859@qq.com
 */

fun nettySizeTable() {
    val sizeTable = mutableListOf<Int>()

    for (i in 16 until 512 step 16) {
        sizeTable.add(i)
    }

}

fun main() {
    val ports = IntArray(5)

    ports.indices.forEach { ports[it] = 5000 + it }
    println(Arrays.toString(ports))

    val selector = Selector.open()
    ports.forEach {
        val serverSocketChannel = ServerSocketChannel.open()
        serverSocketChannel.configureBlocking(false)
        val serverSocket = serverSocketChannel.socket()
        serverSocket.bind(InetSocketAddress(it))

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT)
        println("bind port: $it")
    }

    while (true) {
        val keyNum = selector.select()
        println("key set size: $keyNum")
        val selectedKeys = selector.selectedKeys()

        selectedKeys.forEach {
            if (it.isAcceptable) {
                val channel = it.channel() as ServerSocketChannel
                val socketChannel = channel.accept()
                socketChannel.configureBlocking(false)
                socketChannel.register(selector, SelectionKey.OP_READ)
                selectedKeys.remove(it)
                println("get client connection: $socketChannel")
            } else if (it.isReadable) {
                val socketChannel = it.channel() as SocketChannel
                var bytesRead = 0
                while (true) {
                    val buffer = ByteBuffer.allocate(512)
                    buffer.clear()

                    val read = socketChannel.read(buffer)
                    if (read < 0) {
                        break
                    }

                    buffer.flip()
                    socketChannel.write(buffer)
                    bytesRead += read
                }

                println("read bytes: $bytesRead")
                selectedKeys.remove(it)
            }
        }
    }
}