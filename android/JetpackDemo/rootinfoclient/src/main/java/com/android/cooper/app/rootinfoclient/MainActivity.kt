package com.android.cooper.app.rootinfoclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    private val nettyClient = NettyClientSetup()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nettyClient.registerNetworkState()
    }

    override fun onDestroy() {
        super.onDestroy()
        nettyClient.unregisterNetworkState()
    }
}
