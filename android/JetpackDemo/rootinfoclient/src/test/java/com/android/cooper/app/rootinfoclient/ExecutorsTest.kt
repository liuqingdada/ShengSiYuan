package com.android.cooper.app.rootinfoclient

import com.android.common.utils.SerialExecutor
import org.junit.Test
import java.util.*
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.collections.AbstractList

/**
 * Created by liuqing.yang
 * 2020/10/20.
 * Email: 1239604859@qq.com
 */
class ExecutorsTest {
    private val serialExecutor = SerialExecutor()

    private val indicateRunnable = IndicateRunnable()

    @Test
    fun testSerial() {
        for (i in 0..10) {
            indicateRunnable.putData(
                arrayListOf(
                    byteArrayOf(0),
                    byteArrayOf(1),
                    byteArrayOf(2),
                    byteArrayOf(3),
                    byteArrayOf(4),
                    byteArrayOf(5)
                )
            )
            serialExecutor.execute(indicateRunnable)
            serialExecutor.execute(indicateRunnable)
        }
        Thread {
            while (true) {
                Thread.sleep(100)
                indicateRunnable.next()
            }
        }.start()

        block()
    }

    private fun block() {
        Thread.sleep(Long.MAX_VALUE)
    }

    private class IndicateRunnable : Runnable {
        private val packages = ConcurrentLinkedQueue<ByteArray>()

        private val idle = AtomicBoolean(true)

        /**
         * 场景特殊, 一个 indicate characteristic 对应一个 runnable,
         * 所以 runnable 是全局的, 不能每次 execute的时候都 new 不同的 runnable
         *
         * [serialExecutor] 不同的线程都有可能执行 run, 所以 IndicateRunnable 中存在多线程问题
         */
        override fun run() {
            if (!idle.get()) {
                return
            }
            if (idle.compareAndSet(true, false)) {
                send()
            }
        }

        /**
         * 收到远程的 ACK
         */
        fun next() {
            send()

            // 如果此时没有数据了
            // 证明进入 IDLE 状态
            if (packages.isEmpty()) {
                idle.compareAndSet(false, true)
            }
        }

        fun putData(list: List<ByteArray>) {
            list.forEach {
                packages.offer(it)
            }
        }

        private fun send() {
            val first = packages.poll()
            if (first != null && first.isNotEmpty()) {
                println("send ble data ${first[0]}")
            }
        }
    }
}
