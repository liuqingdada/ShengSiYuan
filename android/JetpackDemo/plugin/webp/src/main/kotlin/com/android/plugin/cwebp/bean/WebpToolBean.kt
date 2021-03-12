package com.android.plugin.cwebp.bean

import java.io.File

object WebpToolBean {
    private lateinit var rootDir: String

    fun setRootDir(rootDir: String) {
        WebpToolBean.rootDir = rootDir
    }

    fun getRootDirPath(): String {
        return rootDir
    }

    fun getToolsDir(): File {
        return File("$rootDir/tools/cwebp")
    }

    fun getToolsDirPath(): String {
        return "$rootDir/tools/cwebp"
    }
}