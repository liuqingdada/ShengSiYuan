package com.android.cooper.app.workmanager.matrix

import android.content.Context
import com.tencent.matrix.plugin.DefaultPluginListener
import com.tencent.matrix.report.Issue
import com.tencent.matrix.util.MatrixLog

/**
 * Created by cooper
 * 20-6-9.
 * Email: 1239604859@qq.com
 */
class SimplePluginListener(context: Context) : DefaultPluginListener(context) {
    companion object {
        private const val TAG = "SimplePluginListener"
    }

    override fun onReportIssue(issue: Issue?) {
        super.onReportIssue(issue)
        MatrixLog.e(TAG, "onReportIssue: ${issue?.toString()}")
    }
}