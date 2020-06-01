package com.android.cooper.app.pagingdemo.model

import androidx.lifecycle.ViewModel
import androidx.paging.Config
import androidx.paging.toLiveData
import com.android.cooper.app.pagingdemo.source.MainListDataSource
import com.android.cooper.app.pagingdemo.source.MainListDataSource2
import com.android.cooper.app.pagingdemo.source.MainListDataSource3

/**
 * Created by liuqing.yang
 * 2020/5/31.
 * Email: 1239604859@qq.com
 */

class MainViewModel : ViewModel() {
    private val pagingConfig = Config(
        pageSize = 30,
        prefetchDistance = 30,
        maxSize = 90,
        enablePlaceholders = false
    )

    val pagingDataList = MainListDataSource3.Factory().toLiveData(pagingConfig)
}