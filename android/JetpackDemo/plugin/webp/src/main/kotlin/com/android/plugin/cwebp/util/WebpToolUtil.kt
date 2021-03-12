package com.android.plugin.cwebp.util

import com.android.plugin.cwebp.bean.WebpToolBean

class WebpToolUtil {
    companion object {
        fun cmd(cmd: String, params: String) {
            val cmdStr = when (System.getProperty("os.name")) {
                "Windows" ->
                    "${WebpToolBean.getToolsDirPath()}/windows/$cmd $params"
                "Mac OS X" ->
                    "${WebpToolBean.getToolsDirPath()}/mac/$cmd $params"
                else ->
                    "${WebpToolBean.getToolsDirPath()}/linux/$cmd $params"
            }
            if (cmd == "") {
                println("Cwebp can't support this system.")
                return
            }
            outputMessage(cmdStr)
        }

        private fun outputMessage(cmdStr: String) {
            val process = Runtime.getRuntime().exec(cmdStr)
            process.waitFor()
        }
    }
}