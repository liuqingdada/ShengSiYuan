package com.android.lib.uicommon.support

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import com.airbnb.mvrx.MvRxView
import com.android.lib.uicommon.R
import com.google.android.material.appbar.AppBarLayout
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by cooper
 * 2021/5/11.
 * Email: 1239604859@qq.com
 */
abstract class BaseFragment (
    @LayoutRes private val contentLayoutId: Int
) : SupportFragment(), MvRxView {
    protected lateinit var appBar: AppBarLayout
    protected lateinit var toolbar: Toolbar

    internal lateinit var contentView: View

    @Suppress("UNCHECKED_CAST")
    protected fun <T : BaseActivity> hostActivity() = _mActivity as T

    protected fun setToolbarTitle(@StringRes title: Int) {
        toolbar.findViewById<TextView>(R.id.title).setText(title)
    }

    protected fun setToolbarTitle(title: String) {
        toolbar.findViewById<TextView>(R.id.title).text = title
    }

    protected fun setToolbarNavigate(@DrawableRes icon: Int) {
        toolbar.setNavigationOnClickListener {
            hostActivity<BaseActivity>().onBackPressedSupport()
        }
        toolbar.setNavigationIcon(icon)
    }

    protected fun showToolbar() {
        appBar.visibility = View.VISIBLE
    }

    protected fun hideToolbar() {
        appBar.visibility = View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.common_content, container, false) as LinearLayout
        appBar = rootView.findViewById(R.id.app_bar)
        toolbar = rootView.findViewById(R.id.toolbar)
        contentView = inflater.inflate(contentLayoutId, null)
        rootView.addView(
            contentView,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        return rootView
    }

    /*private fun setContentLayoutId(@LayoutRes contentLayoutId: Int) {
        try {
            val mContentLayoutId = ReflectionUtils.findField(javaClass, "mContentLayoutId")
            mContentLayoutId.isAccessible = true
            mContentLayoutId.set(this, contentLayoutId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }*/
}
