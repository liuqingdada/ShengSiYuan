package com.shengsiyuan.coroutines.cancel.timeout

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by andy
 * 2019-03-21.
 * Email: 1239604859@qq.com
 */

fun main() = runBlocking {
    val job = launch {
        repeat(1000) {
            println(it)
            delay(500)
        }
    }
    delay(1300)
    println("main: I'm tired of waiting")
    job.cancel() // 取消该任务
    job.join()   // 等待任务执行结束
    println("main: Now I can quite")
    //一旦main函数调用了 job.cancel，我们在其它的协程中就看不到任何输出，因为它被取消了。
    // 这里 也有一个可以使 Job 挂起的函数 cancelAndJoin 它合并了对 cancel 以及 join 的调用。
}