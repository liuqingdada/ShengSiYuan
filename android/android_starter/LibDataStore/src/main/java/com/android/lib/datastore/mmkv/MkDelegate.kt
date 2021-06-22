package com.android.lib.datastore.mmkv

import android.content.SharedPreferences
import android.os.Parcelable
import com.android.lib.datastore.DataStore

/**
 * Created by cooper
 * 21-3-8.
 * Email: 1239604859@qq.com
 */
internal class MkDelegate : DataStore {
    override fun remove(fileName: String, key: String, mode: Int) {
        MkManager.remove(fileName, key, mode)
    }

    override fun removeAll(fileName: String, mode: Int) {
        MkManager.removeAll(fileName, mode)
    }

    override fun edit(fileName: String, mode: Int): SharedPreferences.Editor? {
        return MkManager.edit(fileName, mode)
    }

    override fun registerOnSharedPreferenceChangeListener(
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
    }

    override fun unregisterOnSharedPreferenceChangeListener(
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
    }

    override fun getString(fileName: String, key: String, defValue: String?, mode: Int): String? {
        return MkManager.getString(fileName, key, defValue, mode)
    }

    override fun getInt(fileName: String, key: String, defValue: Int, mode: Int): Int {
        return MkManager.getInt(fileName, key, defValue, mode)
    }

    override fun getLong(fileName: String, key: String, defValue: Long, mode: Int): Long {
        return MkManager.getLong(fileName, key, defValue, mode)
    }

    override fun getBoolean(fileName: String, key: String, defValue: Boolean, mode: Int): Boolean {
        return MkManager.getBoolean(fileName, key, defValue, mode)
    }

    override fun getFloat(fileName: String, key: String, defValue: Float, mode: Int): Float {
        return MkManager.getFloat(fileName, key, defValue, mode)
    }

    override fun getBytes(
        fileName: String,
        key: String,
        defValue: ByteArray,
        mode: Int
    ): ByteArray {
        return MkManager.getBytes(fileName, key, defValue, mode)
    }

    override fun getStringSet(
        fileName: String,
        key: String,
        defValue: Set<String>,
        mode: Int
    ): Set<String> {
        return MkManager.getStringSet(fileName, key, defValue, mode)
    }

    override fun <T : Parcelable> getParcelable(
        fileName: String,
        key: String,
        defValue: T,
        mode: Int
    ): T {
        return MkManager.getParcelable(fileName, key, defValue, mode)
    }

    override fun getAll(prefsName: String): Map<String, *> {
        return hashMapOf<String, String>()
    }

    override fun allKeys(fileName: String, defValue: Array<String>, mode: Int): Array<String> {
        return MkManager.allKeys(fileName, defValue, mode)
    }

    override fun putString(fileName: String, key: String, value: String, mode: Int) {
        MkManager.putString(fileName, key, value, mode)
    }

    override fun putInt(fileName: String, key: String, value: Int, mode: Int) {
        MkManager.putInt(fileName, key, value, mode)
    }

    override fun putLong(fileName: String, key: String, value: Long, mode: Int) {
        MkManager.putLong(fileName, key, value, mode)
    }

    override fun putBoolean(fileName: String, key: String, value: Boolean, mode: Int) {
        MkManager.putBoolean(fileName, key, value, mode)
    }

    override fun putFloat(fileName: String, key: String, value: Float, mode: Int) {
        MkManager.putFloat(fileName, key, value, mode)
    }

    override fun putBytes(fileName: String, key: String, value: ByteArray, mode: Int) {
        MkManager.putBytes(fileName, key, value, mode)
    }

    override fun putStringSet(fileName: String, key: String, value: Set<String>, mode: Int) {
        MkManager.putStringSet(fileName, key, value, mode)
    }

    override fun <T : Parcelable> putParcelable(
        fileName: String,
        key: String,
        value: T,
        mode: Int
    ) {
        MkManager.putParcelable(fileName, key, value, mode)
    }

    override fun importFromSharedPreferences(fileName: String) {
        MkManager.importFromSharedPreferences(fileName)
    }

    override fun reKey(fileName: String, cryptKey: String?) {
        MkManager.reKey(fileName, cryptKey)
    }

    override fun nativeBuffer(fileName: String, key: String, block: (p: Long, size: Int) -> Unit) {
        MkManager.nativeBuffer(fileName, key, block)
    }
}