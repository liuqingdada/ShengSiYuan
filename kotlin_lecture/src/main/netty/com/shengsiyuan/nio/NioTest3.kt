package com.shengsiyuan.nio

import java.net.InetSocketAddress
import java.nio.ByteBuffer
import java.nio.channels.ServerSocketChannel
import java.nio.channels.SocketChannel
import java.util.concurrent.Executors

/**
 * Created by andy
 * 2019-03-15.
 * Email: 1239604859@qq.com
 */

private class Server {
    val messageLength = 2 + 3 + 4

    fun run() {
        val serverSocketChannel = ServerSocketChannel.open()
        serverSocketChannel.socket().bind(InetSocketAddress(8899))
        val executorService = Executors.newSingleThreadExecutor()

        while (true) {
            val socketChannel = serverSocketChannel.accept()
            executorService.execute { handleClient(socketChannel) }
        }
    }

    private fun handleClient(socketChannel: SocketChannel) {
        val buffers = arrayOf(ByteBuffer.allocate(2), ByteBuffer.allocate(3), ByteBuffer.allocate(4))

        while (true) {
            var read: Long = 0

            while (read < messageLength) {
                val r = socketChannel.read(buffers)
                read += r
                println("read = $read")
                buffers.map { "position: ${it.position()}, limit: ${it.limit()}" }.forEach { println(it) }
            }

            buffers.forEach { it.flip() }

            var write: Long = 0

            while (write < messageLength) {
                val w = socketChannel.write(buffers)
                write += w
            }

            buffers.forEach { it.clear() }
            println("read: $read, write: $write, messageLength: $messageLength")
        }
    }
}

fun main() {
    val server = Server()
    server.run()
}