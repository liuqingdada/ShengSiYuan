package com.android.lib.uicommon.browser.sonic;

import android.os.Bundle;
import android.webkit.WebView;

import com.just.agentweb.AgentWeb;
import com.tencent.sonic.sdk.SonicSessionClient;

import java.util.HashMap;

/**
 * A implement of SonicSessionClient which need to connect webview and content data.
 */
public class SonicSessionClientImpl extends SonicSessionClient {

    private AgentWeb mAgentWeb;

    public void bindWebView(AgentWeb agentWeb) {
        this.mAgentWeb = agentWeb;
    }

    public WebView getWebView() {
        return this.mAgentWeb.getWebCreator().getWebView();
    }

    @Override
    public void loadUrl(String url, Bundle extraData) {
        this.mAgentWeb.getUrlLoader().loadUrl(url);
    }

    @Override
    public void loadDataWithBaseUrl(
            String baseUrl,
            String data,
            String mimeType,
            String encoding,
            String historyUrl
    ) {
        this.mAgentWeb.getUrlLoader()
                .loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);

    }

    @Override
    public void loadDataWithBaseUrlAndHeader(
            String baseUrl,
            String data,
            String mimeType,
            String encoding,
            String historyUrl,
            HashMap<String, String> headers
    ) {
        loadDataWithBaseUrl(baseUrl, data, mimeType, encoding, historyUrl);
    }
}
