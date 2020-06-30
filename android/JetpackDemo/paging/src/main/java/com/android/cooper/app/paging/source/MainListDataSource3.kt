package com.android.cooper.app.paging.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.ItemKeyedDataSource
import com.android.common.utils.serialExecute
import com.android.cooper.app.paging.ui.MainData

/**
 * Created by cooper
 * 20-6-1.
 * Email: 1239604859@qq.com
 */
class MainListDataSource3 : ItemKeyedDataSource<Int, MainData>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<MainData>
    ) {
        serialExecute {
            println("loadInitial: ${params.requestedInitialKey}, ${params.requestedLoadSize}")
            callback.onResult(fetchData(0))
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<MainData>) {
        serialExecute {
            println("loadAfter: ${params.key}, ${params.requestedLoadSize}")
            callback.onResult(fetchData(params.key))
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<MainData>) {
        serialExecute {
            println("loadBefore: ${params.key}, ${params.requestedLoadSize}")
            val prePage = params.key.dec()
            if (prePage >= 0) {
                callback.onResult(fetchData(prePage))
            }
        }
    }

    override fun getKey(item: MainData): Int {
        return item.id
    }

    private fun fetchData(start: Int): List<MainData> {
        val end = start + 30
        return (start until end).map { MainData(it, "Cooper@$it") }
    }

    class Factory : DataSource.Factory<Int, MainData>() {
        private val sourceLiveData = MutableLiveData<MainListDataSource3>()

        override fun create(): DataSource<Int, MainData> {
            val latestSource = MainListDataSource3()
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }
}