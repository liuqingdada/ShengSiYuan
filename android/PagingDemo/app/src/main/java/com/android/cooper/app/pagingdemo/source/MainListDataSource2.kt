package com.android.cooper.app.pagingdemo.source

import androidx.paging.PageKeyedDataSource
import com.android.cooper.app.pagingdemo.ui.MainData

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
//        callback.onResult()
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MainData>) {
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MainData>) {
    }
}