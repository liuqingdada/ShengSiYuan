package com.shengsiyuan.kotlin.io

import kotlin.test.Test

/**
 * Created by andy
 * 2019-11-25.
 * Email: 1239604859@qq.com
 */
class ShellTest {
    @Test
    fun shellExe() {
        shell("ls -al")

        shell("cd ..")

        shell("ls -al")
    }

    private fun shell(cmd: String) {
        val p = cmd.execute()

        val exitCode = p.waitFor()

        val text = p.text()

        println("Shell result: $exitCode\n$text")
    }
}

fun String.execute(): Process = Runtime.getRuntime().exec(this)

fun Process.text(): String = inputStream.reader().buffered().readText()