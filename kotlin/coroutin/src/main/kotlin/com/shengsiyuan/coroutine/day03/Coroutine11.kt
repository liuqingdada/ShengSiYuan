package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by liuqing.yang
 * 2020/4/4.
 * Email: 1239604859@qq.com
 *
 * ThreadLocal相关
 */

val threadLocal = ThreadLocal<String>()

fun main() = runBlocking {
    threadLocal.set("Main Value")
    printCurrentThreadValue("start main")

    val job = launch(Dispatchers.Default + threadLocal.asContextElement("Coroutine Value")) {
        printCurrentThreadValue("launch a coroutine")
        yield()
        printCurrentThreadValue("coroutine after yield")
    }

    job.join()

    printCurrentThreadValue("end main")

    testElement(this)
}

private fun printCurrentThreadValue(info: String) {
    println("$info, ${Thread.currentThread().name}, local value is: ${threadLocal.get()}")
}

private suspend fun testElement(scope: CoroutineScope) {
    println("-------------")
    println(Thread.currentThread().name)
    val job = scope.launch(Dispatchers.Default + ThreadContextElementImpl("Progress coroutin")) {
        println(Thread.currentThread().name)
    }
    job.join()
    println(Thread.currentThread().name)
}

class ThreadContextElementImpl(val name: String) : ThreadContextElement<String> {
    companion object Key : CoroutineContext.Key<ThreadContextElementImpl>

    override val key: CoroutineContext.Key<*>
        get() = Key

    override fun updateThreadContext(context: CoroutineContext): String {
        val preName = Thread.currentThread().name
        Thread.currentThread().name = "$preName # $name"
        return preName
    }

    override fun restoreThreadContext(context: CoroutineContext, oldState: String) {
        Thread.currentThread().name = oldState
    }
}
