package com.android.cooper.app.paging.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cooper.app.paging.R
import com.android.cooper.app.paging.databinding.ActivityMainBinding
import com.android.cooper.app.paging.model.MainViewModel
import com.android.cooper.app.paging.qr.VisionActivity
import com.android.lib.uicommon.startActivity
import com.android.lib.uicommon.viewBinding

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding<ActivityMainBinding>()
    private val pageListAdapter = MainPageListAdapter()

    private val viewModle by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        registerComponent()
    }

    private fun initView() {
        binding.recyclerView.adapter = pageListAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btGraph.setOnClickListener {
            Intent(it.context, GraphActivity::class.java).apply {
                startActivity(this)
            }
        }
        binding.btVision.setOnClickListener {
            startActivity<VisionActivity>()
        }
    }

    private fun registerComponent() {
        viewModle.pagingDataList.observe(this) {
            pageListAdapter.submitList(it)
        }
    }
}