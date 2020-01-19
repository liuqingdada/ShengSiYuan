package com.shengsiyuan.kotlin.ota

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.test.Test

/**
 * Created by andy
 * 2020/1/17.
 * Email: 1239604859@qq.com
 */
class Version {
    @Test
    fun formaterTest() {
        val formater = DecimalFormat("0.000000")
        formater.maximumFractionDigits = 1
        formater.groupingSize = 0
        formater.roundingMode = RoundingMode.FLOOR

        val currProgress = 0.000000000000000000001
        val percent = currProgress * 0.5
        val fp = formater.format(percent)
        println(fp)

        val ret = java.lang.Double.valueOf(fp)
        println(ret)

        val ret2 = fp.toDouble()
        println(ret2)
    }
}