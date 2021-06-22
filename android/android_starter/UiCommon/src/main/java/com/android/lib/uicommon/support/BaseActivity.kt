package com.android.lib.uicommon.support

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import com.airbnb.mvrx.MvRxView
import com.android.lib.uicommon.R
import com.google.android.material.appbar.AppBarLayout
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by cooper
 * 2021/5/11.
 * Email: 1239604859@qq.com
 */
abstract class BaseActivity : SupportActivity(), MvRxView {
    protected lateinit var appBar: AppBarLayout
    protected lateinit var toolbar: Toolbar

    protected fun setToolbarTitle(@StringRes title: Int) {
        toolbar.findViewById<TextView>(R.id.title).setText(title)
    }

    protected fun setToolbarTitle(title: String) {
        toolbar.findViewById<TextView>(R.id.title).text = title
    }

    protected fun setToolbarNavigate(@DrawableRes icon: Int) {
        toolbar.setNavigationOnClickListener {
            ActivityCompat.finishAfterTransition(this)
        }
        toolbar.setNavigationIcon(icon)
        /*setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeAsUpIndicator(icon)
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
            setDisplayShowTitleEnabled(true)
            setDisplayShowCustomEnabled(true)
        }*/
    }

    protected fun showToolbar() {
        appBar.visibility = View.VISIBLE
    }

    protected fun hideToolbar() {
        appBar.visibility = View.GONE
    }

    override fun setContentView(layoutResID: Int) {
        setContentView(View.inflate(this, layoutResID, null))
    }

    override fun setContentView(view: View) {
        setContentView(
            view,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
    }

    override fun setContentView(view: View, params: ViewGroup.LayoutParams) {
        initContent(view, params)
    }

    private fun initContent(view: View, params: ViewGroup.LayoutParams) {
        val rootView = View.inflate(this, R.layout.common_content, null) as LinearLayout
        appBar = rootView.findViewById(R.id.app_bar)
        toolbar = rootView.findViewById(R.id.toolbar)
        rootView.addView(view, params)
        super.setContentView(rootView)
    }

    override fun invalidate() {
    }
}