package com.android.cooper.app.paging.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.android.cooper.app.paging.serialExecute
import com.android.cooper.app.paging.ui.MainData

/**
 * Created by cooper
 * 20-6-1.
 * Email: 1239604859@qq.com
 */
class MainListDataSource2 : PageKeyedDataSource<Int, MainData>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MainData>
    ) {
        serialExecute {
            println("loadInitial: ${params.requestedLoadSize}")
            callback.onResult(fetchData(0), 0, 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MainData>) {
        serialExecute {
            println("loadAfter: ${params.key}, ${params.requestedLoadSize}")
            val nextPage = params.key.inc()
            callback.onResult(fetchData(nextPage), nextPage)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MainData>) {
        serialExecute {
            println("loadAfter: ${params.key}, ${params.requestedLoadSize}")
            val prePage = params.key.dec()
            if (prePage >= 0) {
                callback.onResult(fetchData(prePage), prePage)
            }
        }
    }

    private fun fetchData(pageKey: Int): List<MainData> {
        Thread.sleep(1000)
        val start = pageKey * 30
        val end = start + 30
        return (start until end).map { MainData(it, "Cooper_$it") }
    }

    class Factory : DataSource.Factory<Int, MainData>() {
        private val sourceLiveData = MutableLiveData<MainListDataSource2>()

        override fun create(): DataSource<Int, MainData> {
            val latestSource = MainListDataSource2()
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }
}