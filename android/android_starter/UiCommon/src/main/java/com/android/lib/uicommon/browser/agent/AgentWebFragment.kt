package com.android.lib.uicommon.browser.agent

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.android.common.utils.LogUtil
import com.android.lib.uicommon.R
import com.android.lib.uicommon.support.BaseFragment
import com.android.lib.uicommon.theme.getThemeAttrColor
import com.google.gson.Gson
import com.just.agentweb.*
import com.just.agentweb.WebChromeClient
import com.just.agentweb.WebViewClient

/**
 * Created by cooper
 * 2021/6/7.
 * Email: 1239604859@qq.com
 */
open class AgentWebFragment : BaseFragment(R.layout.fragment_agent_web) {
    companion object {
        private const val TAG = "AgentWebFragment"
        const val EXTRA_URL = "url"
    }

    private val gson = Gson()
    protected var url: String? = null
    protected lateinit var agentWeb: AgentWeb
    protected open val middleWareWebChrome: MiddlewareWebChromeBase? = null
    protected open val middleWareWebClient: MiddlewareWebClientBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = arguments ?: throw NullPointerException("AgentWebFragment must be setArguments")
        url = args.getString(EXTRA_URL)
            ?: throw IllegalArgumentException("AgentWebFragment must be set a web url")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val parent = contentView as LinearLayout
        val parentParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        agentWeb = AgentWeb.with(this)
            .setAgentWebParent(parent, -1, parentParams) // 传入AgentWeb的父控件
            .useDefaultIndicator(parent.getThemeAttrColor(R.attr.colorAccent), 1)
            .setAgentWebWebSettings(getSettings()) // 设置 IAgentWebSettings。
            // WebViewClient 与 WebView 使用一致
            // 但是请勿获取WebView调用setWebViewClient(xx)方法了, 会覆盖 AgentWeb DefaultWebClient 同时相应的中间件也会失效
            .setWebViewClient(mWebViewClient)
            .setWebChromeClient(mWebChromeClient)
            .setPermissionInterceptor(mPermissionInterceptor) // 严格模式 Android 4.2.2 以下会放弃注入对象 使用AgentWebView没影响
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .setAgentWebUIController(UIController(hostActivity())) // 参数1是错误显示的布局，参数2点击刷新控件ID -1 表示点击整个布局都刷新
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .useMiddlewareWebChromeKt(middleWareWebChrome) // 设置WebChromeClient中间件
            .useMiddlewareWebClientKt(middleWareWebClient) // 设置WebViewClient中间件
            .additionalHttpHeader(url, "cookie", "serviceToken=null;")
            // 打开其他页面时，弹窗质询用户前往其他应用
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)
            .interceptUnkownUrl() // 拦截找不到相关页面的Url
            .createAgentWeb() // 创建AgentWeb
            .ready() // 设置 WebSettings
            .go(url)

        val webView: WebView = agentWeb.webCreator.webView
        webView.overScrollMode = WebView.OVER_SCROLL_NEVER
        webView.isHorizontalScrollBarEnabled = false
        webView.isVerticalScrollBarEnabled = false
        // mAgentWeb.getWebCreator().getWebView().setOnLongClickListener();
    }

    override fun onResume() {
        agentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onPause() {
        agentWeb.webLifeCycle.onPause()
        super.onPause()
    }

    fun onFragmentKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return agentWeb.handleKeyEvent(keyCode, event)
    }

    override fun onDestroyView() {
        agentWeb.webLifeCycle.onDestroy()
        super.onDestroyView()
    }

    protected fun getSettings(): IAgentWebSettings<*> {
        return object : AbsAgentWebSettings() {
            override fun bindAgentWebSupport(agentWeb: AgentWeb) {
            }
        }
    }

    protected var mPermissionInterceptor = PermissionInterceptor { url, permissions, action ->
        /**
         * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
         * @return true 该Url对应页面请求权限进行拦截 ，false 表示不拦截。
         */
        /**
         * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
         * @return true 该Url对应页面请求权限进行拦截 ，false 表示不拦截。
         */
        LogUtil.i(TAG, "$url, permission: ${gson.toJson(permissions)}, action: $action")
        false
    }

    protected var mWebChromeClient: WebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            LogUtil.d(TAG, "onProgressChanged: $newProgress")
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            if (!TextUtils.isEmpty(title)) {
                setToolbarTitle(title)
            }
        }
    }

    protected var mWebViewClient: WebViewClient = object : WebViewClient() {
        private val timer = HashMap<String, Long>()

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            super.onReceivedError(view, request, error)
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun shouldInterceptRequest(
            view: WebView,
            request: WebResourceRequest
        ): WebResourceResponse? {
            return super.shouldInterceptRequest(view, request)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            LogUtil.d(TAG, "shouldOverrideUrlLoading: ${gson.toJson(view.hitTestResult)}, $url")
            // 优酷想唤起自己应用播放该视频 下面拦截地址返回 true 则会在应用内 H5 播放 禁止优酷唤起播放该视频
            // 如果返回 false DefaultWebClient 会根据 intent 协议处理该地址
            // 首先匹配该应用存不存在 如果存在唤起该应用播放, 如果不存在, 则跳到应用市场下载该应用
            /*if (url.startsWith("intent://") && url.contains("com.youku.phone")) {
                return true;
            }*/
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            super.onPageStarted(view, url, favicon)
            LogUtil.d(TAG, "onPageStarted: $url")
            timer[url] = System.currentTimeMillis()
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            val startTime = timer[url]
            if (startTime != null) {
                val overTime = System.currentTimeMillis()
                LogUtil.d(TAG, "$url:: used time -> ${(overTime - startTime)}")
            }
        }

        /* 错误页回调该方法, 如果重写了该方法, 上面传入了布局将不会显示 */ /*public void onMainFrameError(
                AbsAgentWebUIController agentWebUIController,
                WebView view,
                int errorCode,
                String description,
                String failingUrl
        ) {
        }*/
        override fun onReceivedHttpError(
            view: WebView,
            request: WebResourceRequest,
            errorResponse: WebResourceResponse
        ) {
            super.onReceivedHttpError(view, request, errorResponse)
            LogUtil.d(
                TAG,
                "onReceivedHttpError: %s, %s",
                gson.toJson(request),
                gson.toJson(errorResponse)
            )
        }

        override fun onReceivedSslError(
            view: WebView,
            handler: SslErrorHandler,
            error: SslError
        ) {
            handler.proceed()
            super.onReceivedSslError(view, handler, error)
            LogUtil.d(TAG, "onReceivedSslError: $error")
        }

        override fun onReceivedError(
            view: WebView,
            errorCode: Int,
            description: String,
            failingUrl: String
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            LogUtil.d(
                TAG,
                "onReceivedError: %d, description: %s, errorResponse: %s",
                errorCode,
                description,
                failingUrl
            )
        }
    }

    /**
     * 测试错误页的显示
     */
    protected fun loadErrorWebSite() {
        agentWeb.urlLoader.loadUrl("http://www.unkownwebsiteblog.me")
    }

    /**
     * 清除 WebView 缓存
     */
    protected fun toCleanWebCache() {
        // 清理所有跟 WebView 相关的缓存 数据库 历史记录等
        agentWeb.clearWebCache()
        // 清空所有 AgentWeb 硬盘缓存 包括WebView的缓存 AgentWeb下载的图片 视频 apk 等文件
        // AgentWebConfig.clearDiskCache(this.getContext());
        LogUtil.d(TAG, "clean web cache: ")
    }

    /**
     * 打开浏览器
     *
     * @param targetUrl 外部浏览器打开的地址
     */
    protected fun openBrowser(targetUrl: String) {
        LogUtil.d(TAG, "open in system brower: $targetUrl")
        if (TextUtils.isEmpty(targetUrl) || targetUrl.startsWith("file://")) {
            return
        }
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val mUri = Uri.parse(targetUrl)
        intent.data = mUri
        startActivity(intent)
    }

    override fun invalidate() {
    }
}