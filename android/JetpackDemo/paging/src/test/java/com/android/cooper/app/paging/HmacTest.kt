package com.android.cooper.app.paging

import org.junit.Test
import java.math.BigDecimal
import java.math.BigInteger
import javax.crypto.KeyGenerator
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

/**
 * Created by cooper
 * 20-12-16.
 * Email: 1239604859@qq.com
 */
class HmacTest {
    companion object {
        private const val HMAC_MD5 = "HmacMD5"
        private const val HMAC_SHA512 = "HmacSHA512"
    }

    @Test
    fun encodeAndDecode() {
        val method = HMAC_SHA512

        val keyGen = KeyGenerator.getInstance(method)
        val key = keyGen.generateKey()
        val skey = key.encoded
        println(BigInteger(1, skey).toString(16))

        val mac = Mac.getInstance(method)
        mac.init(key)
        mac.update("HelloWorld".toByteArray())
        val ret = mac.doFinal()
        println(BigInteger(1, ret).toString(16))

        val dkey = SecretKeySpec(skey, method)
        val dmac = Mac.getInstance(method)
        dmac.init(dkey)
        dmac.update("HelloWorld".toByteArray())
        val dret = dmac.doFinal()
        println(BigInteger(1, dret).toString(16))
    }
}