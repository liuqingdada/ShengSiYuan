package com.android.cooper.app.navigation.splash

import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.android.common.utils.WeakHandler
import com.android.cooper.app.navigation.R

/**
 * Created by cooper
 * 21-1-12.
 * Email: 1239604859@qq.com
 * 好难用啊！！！
 */
class SplashFragment : Fragment() {
    companion object {
        private const val TAG = "Splash"

        fun isSignIn(): Boolean = false
    }

    private val handler = WorkHandler(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: ")
        handler.sendEmptyMessageDelayed(1, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: ")
        handler.removeCallbacksAndMessages(null)
    }

    private class WorkHandler(home: SplashFragment) : WeakHandler<SplashFragment>(home) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val host = owner ?: return
            when (msg.what) {
                1 -> {
                    if (isSignIn()) {
                        host.findNavController().navigate(
                            R.id.action_global_mainFragment,
                            null,
                            navOptions {
                                popUpTo = R.id.action_global_mainFragment
                            }
                        )
                    } else {
                        host.findNavController()
                            .navigate(R.id.action_splashFragment_to_loginFragment)
                    }
                }
            }
        }
    }
}