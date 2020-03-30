package com.shengsiyuan.coroutine.day03

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by liuqing.yang
 * 2020/3/27.
 * Email: 1239604859@qq.com
 *
 * 协程与线程之间的关系
 * 协程上下文与分发器(Coroutine Context与Dispatcher)
 * 协程总是会在某个上下文中执行, 这个上下文实际上是由CoroutineContext类型的一个实例来表示的,
 * 该实例是由Kotlin标准来定义的
 *
 * 协程上下文本质上是各种元素所构成的一个集合, 其中, 主要的元素包括协程的Job, 以及分发器
 *
 * 所谓的分发器, 其实主要功能就是确定由哪个线程来执行我们所指定的协程代码
 *
 * 协程上下文包含了一个协程分发器(CoroutineDispatcher), 协程分发器确定了到底由哪个线程或是线程池来执行
 * 我们所指定的协程; 协程分发器可以将协程的执行限制到一个具体指定的线程, 也可以将协程的执行分发到一个线程池中,
 * 由线程池中的某个线程来执行我们所指定的协程, 还可以不加任何限制地去执行我们所指定的协程代码(在这种情况下,
 * 我们所指定的协程代码到底是由哪个线程或线程池来执行的是不确定的, 它需要根据程序的实际执行情况方能确定, 这种
 * 方式的协程分发器在一般的开发中使用较少, 它只用在一些极为特殊的情况下)
 *
 * 所有的协程构建器(Coroutine Builder)如launch何async都会接收一个可选的CoroutineContext参数,
 * 该参数可用于显示指定新协程所运行的分发器以及其他上下文元素
 */

fun main() = runBlocking {
    launch(Dispatchers.Unconfined) {

    }
}























