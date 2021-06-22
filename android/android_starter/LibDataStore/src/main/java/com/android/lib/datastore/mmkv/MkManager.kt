package com.android.lib.datastore.mmkv

import android.content.Context
import android.content.SharedPreferences
import android.os.Parcelable
import com.android.common.utils.ApplicationUtils
import com.android.common.utils.LogUtil
import com.tencent.mmkv.MMKV
import com.tencent.mmkv.MMKVHandler
import com.tencent.mmkv.MMKVLogLevel
import com.tencent.mmkv.MMKVRecoverStrategic

/**
 * Created by cooper
 * 21-3-8.
 * Email: 1239604859@qq.com
 */
internal object MkManager {
    private const val TAG = "MkManager"

    private val context by lazy {
        ApplicationUtils.getApplication()
    }

    fun init() {
        val level = if (LogUtil.isDebug()) MMKVLogLevel.LevelDebug else MMKVLogLevel.LevelInfo
        MMKV.initialize(context, level)
        MMKV.registerHandler(object : MMKVHandler {
            override fun onMMKVCRCCheckFail(error: String?): MMKVRecoverStrategic {
                LogUtil.w(TAG, "onMMKVCRCCheckFail: $error")
                return MMKVRecoverStrategic.OnErrorRecover
            }

            override fun onMMKVFileLengthError(error: String?): MMKVRecoverStrategic {
                LogUtil.w(TAG, "onMMKVFileLengthError: $error")
                return MMKVRecoverStrategic.OnErrorRecover
            }

            override fun wantLogRedirecting(): Boolean = true

            override fun mmkvLog(
                level: MMKVLogLevel?,
                file: String?,
                line: Int,
                func: String?,
                message: String?
            ) {
                val log = "$file:$line:$func: $message"
                when (level) {
                    MMKVLogLevel.LevelDebug -> {
                        LogUtil.d(TAG, "mmkvLog: $log")
                    }
                    MMKVLogLevel.LevelInfo -> {
                        LogUtil.i(TAG, "mmkvLog: $log")
                    }
                    MMKVLogLevel.LevelWarning -> {
                        LogUtil.w(TAG, "mmkvLog: $log")
                    }
                    MMKVLogLevel.LevelError -> {
                        LogUtil.e(TAG, "mmkvLog: $log")
                    }
                    MMKVLogLevel.LevelNone -> {
                        LogUtil.e(TAG, "mmkvLog LevelNone: $log")
                    }
                }
            }
        })
        MMKV.registerContentChangeNotify {
            LogUtil.i(TAG, "Other process has changed content of: $it")
        }
    }

    private fun open(fileName: String, mode: Int = MMKV.SINGLE_PROCESS_MODE): MMKV? {
        return MMKV.mmkvWithID(fileName, mode)
    }

    fun edit(fileName: String, mode: Int): SharedPreferences.Editor? {
        return open(fileName, mode)?.edit()
    }

    fun remove(fileName: String, key: String, mode: Int) {
        open(fileName, mode)?.removeValueForKey(key)
    }

    fun removeAll(fileName: String, mode: Int) {
        open(fileName, mode)?.clearAll()
    }

    fun getString(fileName: String, key: String, defValue: String?, mode: Int): String? {
        open(fileName, mode)?.getString(key, defValue)?.let { return it }
        return defValue
    }

    fun getInt(fileName: String, key: String, defValue: Int, mode: Int): Int {
        open(fileName, mode)?.getInt(key, defValue)?.let { return it }
        return defValue
    }

    fun getLong(fileName: String, key: String, defValue: Long, mode: Int): Long {
        open(fileName, mode)?.getLong(key, defValue)?.let { return it }
        return defValue
    }

    fun getBoolean(fileName: String, key: String, defValue: Boolean, mode: Int): Boolean {
        open(fileName, mode)?.getBoolean(key, defValue)?.let { return it }
        return defValue
    }

    fun getFloat(fileName: String, key: String, defValue: Float, mode: Int): Float {
        open(fileName, mode)?.getFloat(key, defValue)?.let { return it }
        return defValue
    }

    fun getBytes(fileName: String, key: String, defValue: ByteArray, mode: Int): ByteArray {
        open(fileName, mode)?.getBytes(key, defValue)?.let { return it }
        return defValue
    }

    fun getStringSet(fileName: String, key: String, defValue: Set<String>, mode: Int): Set<String> {
        open(fileName, mode)?.getStringSet(key, defValue)?.let { return it }
        return defValue
    }

    fun allKeys(fileName: String, defValue: Array<String>, mode: Int): Array<String> {
        open(fileName, mode)?.allKeys()?.let { return it }
        return defValue
    }

    fun <T : Parcelable> getParcelable(fileName: String, key: String, defValue: T, mode: Int): T {
        open(fileName, mode)?.decodeParcelable(key, defValue.javaClass, defValue)?.let { return it }
        return defValue
    }

    fun putString(fileName: String, key: String, value: String, mode: Int) {
        open(fileName, mode)?.putString(key, value)
    }

    fun putInt(fileName: String, key: String, value: Int, mode: Int) {
        open(fileName, mode)?.putInt(key, value)
    }

    fun putLong(fileName: String, key: String, value: Long, mode: Int) {
        open(fileName, mode)?.putLong(key, value)
    }

    fun putBoolean(fileName: String, key: String, value: Boolean, mode: Int) {
        open(fileName, mode)?.putBoolean(key, value)
    }

    fun putFloat(fileName: String, key: String, value: Float, mode: Int) {
        open(fileName, mode)?.putFloat(key, value)
    }

    fun putBytes(fileName: String, key: String, value: ByteArray, mode: Int) {
        open(fileName, mode)?.putBytes(key, value)
    }

    fun putStringSet(fileName: String, key: String, value: Set<String>, mode: Int) {
        open(fileName, mode)?.putStringSet(key, value)
    }

    fun <T : Parcelable> putParcelable(fileName: String, key: String, value: T, mode: Int) {
        open(fileName, mode)?.encode(key, value)
    }

    fun importFromSharedPreferences(fileName: String) {
        val mmkv = open(fileName)
        val sp = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        mmkv?.importFromSharedPreferences(sp)
        sp.edit().clear().apply()
    }

    fun reKey(fileName: String, cryptKey: String?) {
        val mmkv = open(fileName)
        mmkv?.reKey(cryptKey)
    }

    /**
     * 当从 MMKV 取一个 String or byte[]的时候，会有一次从 native 到 JVM 的内存拷贝
     * 如果这个值立即传递到另一个 native 库(JNI)，又会有一次从 JVM 到 native 的内存拷贝
     * 当这个值比较大的时候，整个过程会非常浪费。Native Buffer 就是为了解决这个问题
     */
    fun nativeBuffer(fileName: String, key: String, block: (p: Long, size: Int) -> Unit) {
        open(fileName)?.let { kv ->
            val sizeNeeded = kv.getValueActualSize(key)
            MMKV.createNativeBuffer(sizeNeeded)?.let { buffer ->
                try {
                    val size = kv.writeValueToNativeBuffer(key, buffer)
                    LogUtil.d(TAG, "nativeBuffer: size needed: $sizeNeeded, wtitten size: $size")

                    // pass native buffer to another native library
                    block(buffer.pointer, buffer.size)
                } finally {
                    MMKV.destroyNativeBuffer(buffer)
                }
            }
        }
    }
}