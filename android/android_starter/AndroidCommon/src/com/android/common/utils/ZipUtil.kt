package com.android.common.utils

import com.blankj.utilcode.util.ZipUtils
import java.io.File
import java.io.IOException

/**
 * Created by cooper
 * 2021/5/18.
 * Email: 1239604859@qq.com
 */
object ZipUtil {
    @Throws(IOException::class)
    fun unzip(zip: File, out: File) {
        ZipUtils.unzipFile(zip, out)
    }
}