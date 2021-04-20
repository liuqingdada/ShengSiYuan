package com.android.logic.common.debug

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.Process
import androidx.annotation.StringDef
import androidx.appcompat.app.AppCompatActivity
import com.android.common.utils.ApplicationUtils
import com.android.common.utils.LogUtil
import com.android.lib.datastore.DsManager
import com.android.lib.uicommon.viewBinding
import com.android.logic.common.R
import com.android.logic.common.app.AppConfig
import com.android.logic.common.databinding.ActivityDebugBinding
import kotlin.system.exitProcess

/**
 * Created by cooper
 * 21-1-21.
 * Email: 1239604859@qq.com
 */
object Debug {
    private const val PROFILE_DEBUG = "profile_debug"

    private val context: Context by lazy {
        ApplicationUtils.getApplication()
    }

    fun exit() {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        am.runningAppProcesses.forEach {
            if (it.pid != Process.myPid()) {
                Process.killProcess(it.pid)
            }
        }
        Process.killProcess(Process.myPid())
        exitProcess(0)
    }

    fun isDebug() = getBoolean(Profile.SP_DEBUG, false)

    fun isDelayStart() = getBoolean(Profile.SP_DEBUG_DELAY_START, true)

    fun setBoolean(@Profile key: String, value: Boolean) {
        DsManager.delegate().putBoolean(PROFILE_DEBUG, key, value)
    }

    private fun getBoolean(@Profile key: String, default: Boolean) =
        DsManager.delegate().getBoolean(PROFILE_DEBUG, key, default)
}

class DebugActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityDebugBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)
        initView()
        listenView()
    }

    private fun initView() {
        binding.switchDebug.isChecked = Debug.isDebug()
    }

    private fun listenView() {
        binding.switchDebug.setOnCheckedChangeListener { _, isChecked ->
            Debug.setBoolean(Profile.SP_DEBUG, isChecked)
            LogUtil.setDebug(AppConfig.isDebug())
        }
    }
}

@StringDef(
    Profile.SP_DEBUG,
)
annotation class Profile {
    companion object {
        const val SP_DEBUG = "sp_key_debug"
        const val SP_DEBUG_DELAY_START = "sp_debug_delay_start"
    }
}