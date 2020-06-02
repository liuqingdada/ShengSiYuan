package com.android.cooper.app.paging.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PositionalDataSource
import com.android.cooper.app.paging.serialExecute
import com.android.cooper.app.paging.ui.MainData

/**
 * Created by liuqing.yang
 * 2020/5/31.
 * Email: 1239604859@qq.com
 */

class MainListDataSource : PositionalDataSource<MainData>() {
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MainData>) {
        serialExecute {
            println("loadRange: ${params.startPosition}, ${params.loadSize}")
            callback.onResult(fetchData(params.startPosition, params.loadSize))
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MainData>) {
        serialExecute {
            println("loadInitial: ${params.requestedStartPosition}, ${params.pageSize}")
            callback.onResult(fetchData(params.requestedStartPosition, params.pageSize), 0)
        }
    }

    private fun fetchData(start: Int, size: Int): List<MainData> {
        Thread.sleep(1000)
        val end = start + size
        return (start until end).map { MainData(it, "Cooper $it") }
    }

    class Factory : DataSource.Factory<Int, MainData>() {
        private val sourceLiveData = MutableLiveData<MainListDataSource>()

        override fun create(): DataSource<Int, MainData> {
            val latestSource = MainListDataSource()
            sourceLiveData.postValue(latestSource)
            return latestSource
        }
    }
}
