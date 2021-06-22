package com.android.lib.datastore.sp

import android.content.SharedPreferences
import android.os.Parcelable
import com.android.common.utils.ApplicationUtils
import com.android.lib.datastore.DataStore

/**
 * Created by cooper
 * 21-3-8.
 * Email: 1239604859@qq.com
 */
internal class SpDelegate : DataStore {
    private val context by lazy {
        ApplicationUtils.getApplication()
    }

    override fun remove(fileName: String, key: String, mode: Int) {
        SpManager.remove(context, fileName, key)
    }

    override fun removeAll(fileName: String, mode: Int) {
        SpManager.removeAll(context, fileName)
    }

    override fun edit(fileName: String, mode: Int): SharedPreferences.Editor {
        return SpManager.edit(context, fileName)
    }

    override fun registerOnSharedPreferenceChangeListener(
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        SpManager.registerOnSharedPreferenceChangeListener(context, prefsName, listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        SpManager.unregisterOnSharedPreferenceChangeListener(context, prefsName, listener)
    }

    override fun getString(fileName: String, key: String, defValue: String?, mode: Int): String? {
        return SpManager.getString(context, fileName, key, defValue)
    }

    override fun getInt(fileName: String, key: String, defValue: Int, mode: Int): Int {
        return SpManager.getInt(context, fileName, key, defValue)
    }

    override fun getLong(fileName: String, key: String, defValue: Long, mode: Int): Long {
        return SpManager.getLong(context, fileName, key, defValue)
    }

    override fun getBoolean(fileName: String, key: String, defValue: Boolean, mode: Int): Boolean {
        return SpManager.getBoolean(context, fileName, key, defValue)
    }

    override fun getFloat(fileName: String, key: String, defValue: Float, mode: Int): Float {
        return SpManager.getFloat(context, fileName, key, defValue)
    }

    override fun getBytes(
        fileName: String,
        key: String,
        defValue: ByteArray,
        mode: Int
    ): ByteArray {
        throw UnsupportedOperationException("Sp unsupport byte array")
    }

    override fun getStringSet(
        fileName: String,
        key: String,
        defValue: Set<String>,
        mode: Int
    ): Set<String> {
        return SpManager.getStringSet(context, fileName, key, defValue)
    }

    override fun <T : Parcelable> getParcelable(
        fileName: String,
        key: String,
        defValue: T,
        mode: Int
    ): T {
        throw UnsupportedOperationException("Sp unsupport parcelable")
    }

    override fun getAll(prefsName: String): Map<String, *> {
        return SpManager.getAll(context, prefsName)
    }

    override fun allKeys(fileName: String, defValue: Array<String>, mode: Int): Array<String> {
        return arrayOf()
    }

    override fun putString(fileName: String, key: String, value: String, mode: Int) {
        SpManager.putString(context, fileName, key, value)
    }

    override fun putInt(fileName: String, key: String, value: Int, mode: Int) {
        SpManager.putInt(context, fileName, key, value)
    }

    override fun putLong(fileName: String, key: String, value: Long, mode: Int) {
        SpManager.putLong(context, fileName, key, value)
    }

    override fun putBoolean(fileName: String, key: String, value: Boolean, mode: Int) {
        SpManager.putBoolean(context, fileName, key, value)
    }

    override fun putFloat(fileName: String, key: String, value: Float, mode: Int) {
        SpManager.putFloat(context, fileName, key, value)
    }

    override fun putBytes(fileName: String, key: String, value: ByteArray, mode: Int) {
        throw UnsupportedOperationException("Sp unsupport byte array")
    }

    override fun putStringSet(fileName: String, key: String, value: Set<String>, mode: Int) {
        SpManager.putStringSet(context, fileName, key, value)
    }

    override fun <T : Parcelable> putParcelable(
        fileName: String,
        key: String,
        value: T,
        mode: Int
    ) {
        throw UnsupportedOperationException("Sp unsupport parcelable")
    }

    override fun importFromSharedPreferences(fileName: String) {
        // Nope
    }

    override fun reKey(fileName: String, cryptKey: String?) {
        // Nope
    }

    override fun nativeBuffer(fileName: String, key: String, block: (p: Long, size: Int) -> Unit) {
        throw UnsupportedOperationException("Sp unsupport native buffer")
    }
}