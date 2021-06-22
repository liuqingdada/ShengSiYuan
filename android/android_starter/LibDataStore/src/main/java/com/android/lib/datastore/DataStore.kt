package com.android.lib.datastore

import android.content.SharedPreferences
import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 * Created by cooper
 * 21-3-8.
 * Email: 1239604859@qq.com
 */
interface DataStore {

    fun remove(fileName: String, key: String, mode: Int = SINGLE_PROCESS_MODE)

    fun removeAll(fileName: String, mode: Int = SINGLE_PROCESS_MODE)

    fun edit(fileName: String, mode: Int = SINGLE_PROCESS_MODE): SharedPreferences.Editor?

    fun registerOnSharedPreferenceChangeListener(
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    )

    fun unregisterOnSharedPreferenceChangeListener(
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    )

    fun getString(
        fileName: String,
        key: String,
        defValue: String?,
        mode: Int = SINGLE_PROCESS_MODE
    ): String?

    fun getInt(fileName: String, key: String, defValue: Int, mode: Int = SINGLE_PROCESS_MODE): Int

    fun getLong(
        fileName: String,
        key: String,
        defValue: Long,
        mode: Int = SINGLE_PROCESS_MODE
    ): Long

    fun getBoolean(
        fileName: String,
        key: String,
        defValue: Boolean,
        mode: Int = SINGLE_PROCESS_MODE
    ): Boolean

    fun getFloat(
        fileName: String,
        key: String,
        defValue: Float,
        mode: Int = SINGLE_PROCESS_MODE
    ): Float

    fun getBytes(
        fileName: String,
        key: String,
        defValue: ByteArray,
        mode: Int = SINGLE_PROCESS_MODE
    ): ByteArray

    fun getStringSet(
        fileName: String,
        key: String,
        defValue: Set<String>,
        mode: Int = SINGLE_PROCESS_MODE
    ): Set<String>

    fun <T : Parcelable> getParcelable(
        fileName: String,
        key: String,
        defValue: T,
        mode: Int = SINGLE_PROCESS_MODE
    ): T

    fun getAll(prefsName: String): Map<String, *>

    fun allKeys(
        fileName: String,
        defValue: Array<String>,
        mode: Int = SINGLE_PROCESS_MODE
    ): Array<String>

    fun putString(fileName: String, key: String, value: String, mode: Int = SINGLE_PROCESS_MODE)

    fun putInt(fileName: String, key: String, value: Int, mode: Int = SINGLE_PROCESS_MODE)

    fun putLong(fileName: String, key: String, value: Long, mode: Int = SINGLE_PROCESS_MODE)

    fun putBoolean(fileName: String, key: String, value: Boolean, mode: Int = SINGLE_PROCESS_MODE)

    fun putFloat(fileName: String, key: String, value: Float, mode: Int = SINGLE_PROCESS_MODE)

    fun putBytes(fileName: String, key: String, value: ByteArray, mode: Int = SINGLE_PROCESS_MODE)

    fun putStringSet(
        fileName: String,
        key: String,
        value: Set<String>,
        mode: Int = SINGLE_PROCESS_MODE
    )

    fun <T : Parcelable> putParcelable(
        fileName: String,
        key: String,
        value: T,
        mode: Int = SINGLE_PROCESS_MODE
    )

    fun importFromSharedPreferences(fileName: String)

    fun reKey(fileName: String, cryptKey: String?)

    fun nativeBuffer(fileName: String, key: String, block: (p: Long, size: Int) -> Unit)

    companion object {
        const val SINGLE_PROCESS_MODE = MMKV.SINGLE_PROCESS_MODE
        const val MULTI_PROCESS_MODE = MMKV.MULTI_PROCESS_MODE
    }
}