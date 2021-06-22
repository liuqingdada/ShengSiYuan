package com.android.lib.uicommon.browser.agent

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.android.lib.uicommon.browser.sonic.SonicImpl
import com.android.lib.uicommon.browser.sonic.SonicJavaScriptInterface
import com.android.lib.uicommon.browser.sonic.SonicJavaScriptInterface.PARAM_CLICK_TIME
import com.just.agentweb.MiddlewareWebClientBase

/**
 * Created by andy
 * 2019-10-29.
 * Email: 1239604859@qq.com
 */
class VasSonicFragment : AgentWebFragment() {
    companion object {
        private const val TAG = "VasSonicFragment"

        @JvmStatic
        fun newInstance(url: String?): VasSonicFragment = VasSonicFragment().apply {
            val param = Bundle()
            param.putString(EXTRA_URL, url)
            arguments = param
        }
    }

    private var mSonicImpl: SonicImpl? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val realUrl = arguments?.getString(EXTRA_URL)

        // 1. 首先创建SonicImpl
        mSonicImpl = SonicImpl(realUrl, this.context)
        // 2. 调用 onCreateSession
        mSonicImpl?.onCreateSession()
        // 3. 创建AgentWeb 注意创建AgentWeb的时候应该使用加入SonicWebViewClient中间件
        super.onViewCreated(view, savedInstanceState)
        // 4. 注入 JavaScriptInterface
        agentWeb.jsInterfaceHolder.addJavaObject(
            "sonic",
            SonicJavaScriptInterface(
                mSonicImpl?.sonicSessionClient,
                Intent().putExtra(PARAM_CLICK_TIME, arguments?.getLong(PARAM_CLICK_TIME))
                    .putExtra("loadUrlTime", System.currentTimeMillis())
            )
        )
        // 5. 最后绑定AgentWeb
        mSonicImpl?.bindAgentWeb(agentWeb)
    }

    // 在步骤3的时候应该传入给 AgentWeb
    override val middleWareWebClient: MiddlewareWebClientBase?
        get() = mSonicImpl?.createSonicClientMiddleWare()

    override fun onDestroyView() {
        super.onDestroyView()
        mSonicImpl?.destrory()
    }
}
