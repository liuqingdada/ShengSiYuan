package com.android.lib.uicommon.browser

import android.os.Bundle
import android.view.KeyEvent
import com.android.lib.uicommon.R
import com.android.lib.uicommon.browser.agent.VasSonicFragment
import com.android.lib.uicommon.support.BaseActivity

/**
 * Created by cooper
 * 2021/6/7.
 * Email: 1239604859@qq.com
 */
class BrowserActivity : BaseActivity() {
    companion object {
        private const val TAG = "BrowserActivity"
        const val EXTRA_URL = "url"
    }

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        url = intent.getStringExtra(EXTRA_URL)
        setContentView(R.layout.activity_fragment_container)
        loadRootFragment()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val rootFragment = findFragment(VasSonicFragment::class.java)
        if (rootFragment != null) {
            return if (rootFragment.onFragmentKeyDown(keyCode, event)) {
                true
            } else {
                super.onKeyDown(keyCode, event)
            }
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun loadRootFragment() {
        val rootFragment = findFragment(VasSonicFragment::class.java)
        if (rootFragment == null) {
            loadRootFragment(R.id.root, VasSonicFragment.newInstance(url))
        }
    }
}