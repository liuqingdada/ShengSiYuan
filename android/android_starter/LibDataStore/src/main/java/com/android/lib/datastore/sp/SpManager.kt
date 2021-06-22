package com.android.lib.datastore.sp

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by cooper
 * 21-3-8.
 * Email: 1239604859@qq.com
 */
internal object SpManager {
    private fun open(cxt: Context, prefsName: String): SharedPreferences {
        return cxt.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    fun registerOnSharedPreferenceChangeListener(
        cxt: Context,
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        open(cxt, prefsName).registerOnSharedPreferenceChangeListener(listener)
    }

    fun unregisterOnSharedPreferenceChangeListener(
        cxt: Context,
        prefsName: String,
        listener: SharedPreferences.OnSharedPreferenceChangeListener
    ) {
        open(cxt, prefsName).unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun edit(cxt: Context, prefsName: String): SharedPreferences.Editor {
        return open(cxt, prefsName).edit()
    }

    fun remove(cxt: Context, prefsName: String, key: String) {
        open(cxt, prefsName).edit().remove(key).apply()
    }

    fun removeAll(cxt: Context, prefsName: String) {
        open(cxt, prefsName).edit().clear().apply()
    }

    fun getString(cxt: Context, prefsName: String, key: String, defValue: String?): String? {
        return open(cxt, prefsName).getString(key, defValue)
    }

    fun getInt(cxt: Context, prefsName: String, key: String, defValue: Int): Int {
        return open(cxt, prefsName).getInt(key, defValue)
    }

    fun getLong(cxt: Context, prefsName: String, key: String, defValue: Long): Long {
        return open(cxt, prefsName).getLong(key, defValue)
    }

    fun getBoolean(cxt: Context, prefsName: String, key: String, defValue: Boolean): Boolean {
        return open(cxt, prefsName).getBoolean(key, defValue)
    }

    fun getFloat(cxt: Context, prefsName: String, key: String, defValue: Float): Float {
        return open(cxt, prefsName).getFloat(key, defValue)
    }

    fun getStringSet(
        cxt: Context,
        prefsName: String,
        key: String,
        defValue: Set<String>
    ): Set<String> {
        open(cxt, prefsName).getStringSet(key, defValue)?.let { return it }
        return defValue
    }

    fun getAll(cxt: Context, prefsName: String): Map<String, *> {
        return open(cxt, prefsName).all
    }

    fun putString(cxt: Context, prefsName: String, key: String, value: String) {
        open(cxt, prefsName).edit().putString(key, value).apply()
    }

    fun putInt(cxt: Context, prefsName: String, key: String, value: Int) {
        open(cxt, prefsName).edit().putInt(key, value).apply()
    }

    fun putLong(cxt: Context, prefsName: String, key: String, value: Long) {
        open(cxt, prefsName).edit().putLong(key, value).apply()
    }

    fun putBoolean(cxt: Context, prefsName: String, key: String, value: Boolean) {
        open(cxt, prefsName).edit().putBoolean(key, value).apply()
    }

    fun putFloat(cxt: Context, prefsName: String, key: String, value: Float) {
        open(cxt, prefsName).edit().putFloat(key, value).apply()
    }

    fun putStringSet(cxt: Context, prefsName: String, key: String, value: Set<String>) {
        open(cxt, prefsName).edit().putStringSet(key, value).apply()
    }
}