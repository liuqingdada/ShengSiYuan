package com.android.lib.uicommon.browser.agent

import com.just.agentweb.AgentWeb
import com.just.agentweb.MiddlewareWebChromeBase
import com.just.agentweb.MiddlewareWebClientBase

/**
 * Created by cooper
 * 2021/6/7.
 * Email: 1239604859@qq.com
 */

fun AgentWeb.CommonBuilder.useMiddlewareWebChromeKt(
    middlewareWebChromeBase: MiddlewareWebChromeBase?
): AgentWeb.CommonBuilder {
    middlewareWebChromeBase?.let { useMiddlewareWebChrome(it) }
    return this
}

fun AgentWeb.CommonBuilder.useMiddlewareWebClientKt(
    middleWrareWebClientBase: MiddlewareWebClientBase?
): AgentWeb.CommonBuilder {
    middleWrareWebClientBase?.let { useMiddlewareWebClient(it) }
    return this
}