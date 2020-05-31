package com.android.cooper.app.pagingdemo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cooper.app.pagingdemo.R
import com.android.cooper.app.pagingdemo.model.MainViewModel
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
    }

    private fun registerComponent() {
        viewModle.pagingDataList.observe(this, Observer {
            println(it)
            pageListAdapter.submitList(it)
        })
    }
}