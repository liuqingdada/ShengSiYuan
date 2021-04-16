package com.android.lib.datastore

import com.android.lib.datastore.mmkv.MkDelegate
import com.android.lib.datastore.mmkv.MkManager
import com.android.lib.datastore.sp.SpDelegate
import com.tencent.mmkv.MMKV

/**
 * Created by cooper
 * 21-3-8.
 * Email: 1239604859@qq.com
 */
object DsManager {
    private var delegate: DataStore? = null

    private var delegateFlag = DataStoreDelegate.MMKV

    enum class DataStoreDelegate {
        SP,
        MMKV,
    }

    fun init(flag: DataStoreDelegate = DataStoreDelegate.MMKV) {
        delegateFlag = flag

        delegate = when (delegateFlag) {
            DataStoreDelegate.SP -> {
                SpDelegate()
            }
            DataStoreDelegate.MMKV -> {
                MkManager.init()
                MkDelegate()
            }
        }
    }

    fun delegate(): DataStore = delegate!!
}