package com.android.cooper.app.paging.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cooper.app.paging.R
import com.android.cooper.app.paging.model.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        recyclerView.adapter = pageListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        btGraph.setOnClickListener {
            Intent(it.context, GraphActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

    private fun registerComponent() {
        viewModle.pagingDataList.observe(this, Observer {
            pageListAdapter.submitList(it)
        })
    }
}