package com.android.cooper.app.workmanager.matrix

import android.content.Context
import android.content.Intent
import com.android.common.utils.ApplicationUtils
import com.android.common.utils.LogUtil
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig
import com.tencent.matrix.resource.ResourcePlugin
import com.tencent.matrix.resource.config.ResourceConfig
import com.tencent.matrix.trace.TracePlugin
import com.tencent.matrix.trace.config.TraceConfig
import com.tencent.matrix.util.MatrixLog
import com.tencent.sqlitelint.SQLiteLint
import com.tencent.sqlitelint.SQLiteLintPlugin
import com.tencent.sqlitelint.config.SQLiteLintConfig

/**
 * Created by cooper
 * 20-6-9.
 * Email: 1239604859@qq.com
 */
object MatrixHelper {
    private const val TAG = "MatrixHelper"

    @JvmStatic
    fun main(vararg args: String) {
        val context = ApplicationUtils.getApplication()
        if (ApplicationUtils.isMainProcess(context)) {
            MatrixLog.setMatrixLogImp(matrixLogImpl)

            val dynamicConfig = DynamicConfig()
            val matrixEnable = dynamicConfig.isMatrixEnable()
            val fpsEnable = dynamicConfig.isFPSEnable()
            val traceEnable = dynamicConfig.isTraceEnable()

            MatrixLog.d(TAG, "Matrix helper main: ")

            val builder = Matrix.Builder(context)
            builder.patchListener(SimplePluginListener(context))

            val traceConfig = TraceConfig.Builder()
                .dynamicConfig(dynamicConfig)
                .enableFPS(fpsEnable)
                .enableEvilMethodTrace(traceEnable)
                .enableAnrTrace(traceEnable)
                .enableStartup(traceEnable)
                .splashActivities(resolveActivity(context))
                .isDebug(true)
                .isDevEnv(false)
                .build()
            val tracePlugin = TracePlugin(traceConfig)

            builder.plugin(tracePlugin)

            if (matrixEnable) {
                val intent = Intent()
                val mode = ResourceConfig.DumpMode.AUTO_DUMP
                MatrixLog.d(TAG, "Dump activity leak mode: $mode")
                intent.setClassName(
                    context.packageName,
                    "com.tencent.mm.ui.matrix.ManualDumpActivity"
                )
                val resourceConfig = ResourceConfig.Builder()
                    .dynamicConfig(dynamicConfig)
                    .setAutoDumpHprofMode(mode)
                    .setNotificationContentIntent(intent)
                    .build()

                builder.plugin(ResourcePlugin(resourceConfig))

                val ioCanaryPlugin = IOCanaryPlugin(
                    IOConfig.Builder()
                        .dynamicConfig(dynamicConfig)
                        .build()
                )

                builder.plugin(ioCanaryPlugin)

                val sqlLiteConfig: SQLiteLintConfig = try {
                    SQLiteLintConfig(SQLiteLint.SqlExecutionCallbackMode.CUSTOM_NOTIFY)
                } catch (t: Throwable) {
                    SQLiteLintConfig(SQLiteLint.SqlExecutionCallbackMode.CUSTOM_NOTIFY)
                }
                builder.plugin(SQLiteLintPlugin(sqlLiteConfig))
            }

            Matrix.init(builder.build())

            // start only startup tracer, close other tracer
            tracePlugin.start()
        }
    }

    private val matrixLogImpl = object : MatrixLog.MatrixLogImp {
        override fun v(tag: String?, format: String, vararg params: Any?) {
            val log = if (params.isEmpty()) format else String.format(format, *params)
            LogUtil.v(tag, log)
        }

        override fun i(tag: String?, format: String, vararg params: Any?) {
            val log = if (params.isEmpty()) format else String.format(format, *params)
            LogUtil.i(tag, log)
        }

        override fun d(tag: String?, format: String, vararg params: Any?) {
            val log = if (params.isEmpty()) format else String.format(format, *params)
            LogUtil.d(tag, log)
        }

        override fun w(tag: String?, format: String, vararg params: Any?) {
            val log = if (params.isEmpty()) format else String.format(format, *params)
            LogUtil.w(tag, log)
        }

        override fun e(tag: String?, format: String, vararg params: Any?) {
            val log = if (params.isEmpty()) format else String.format(format, *params)
            LogUtil.e(tag, log)
        }

        override fun printErrStackTrace(
            tag: String?,
            tr: Throwable?,
            format: String,
            vararg params: Any?
        ) {
            val log = if (params.isEmpty()) format else String.format(format, *params)
            val stackTrace = "$log  ${LogUtil.getStackTraceString(tr)}"
            LogUtil.e(tag, stackTrace)
        }
    }

    private fun getLauncherIntent(context: Context): Intent? {
        var intent: Intent? = null
        try {
            val pm = context.packageManager
            intent = pm.getLaunchIntentForPackage(context.packageName)
        } catch (e: Exception) {
            LogUtil.d(TAG, "getLauncherIntent: ", e)
        }
        return intent
    }

    private fun resolveActivity(context: Context): String {
        val intent = getLauncherIntent(context)
        val name = intent?.component?.className
        LogUtil.d(TAG, "resolveActivity: $name")
        return "$name;"
    }
}
