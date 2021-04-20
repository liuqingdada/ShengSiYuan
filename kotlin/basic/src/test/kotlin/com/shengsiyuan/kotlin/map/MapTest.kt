package com.shengsiyuan.kotlin.map

import kotlin.test.Test

/**
 * Created by cooper
 * 2021/4/20.
 * Email: 1239604859@qq.com
 */

class MapTest {
    @Test
    fun testHashCode() {
        val obj = Any()
        val hashCode = obj.hashCode()
        println(hashCode.toString(2))
        println((hashCode.ushr(16)).toString(2))
        val rHash = hashCode.xor(hashCode.ushr(16))
        println(rHash)
        println(rHash.toString(2))
        println(rHash.and(15))
    }
}
