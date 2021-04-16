package com.android.lib.uicommon

import androidx.annotation.LayoutRes
import com.airbnb.mvrx.MvRxView
import com.android.lib.uicommon.support.MiuixFragment

/**
 * Created by cooper
 * 21-4-7.
 * Email: 1239604859@qq.com
 */
abstract class CommonFragment @JvmOverloads constructor(
    @LayoutRes contentLayoutId: Int = 0,
) : MiuixFragment(contentLayoutId), MvRxView