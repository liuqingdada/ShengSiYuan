package com.android.cooper.app.paging.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.cooper.app.paging.R

/**
 * Created by liuqing.yang
 * 2020/5/31.
 * Email: 1239604859@qq.com
 */

class MainPageListAdapter : PagedListAdapter<MainData, MainListViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MainData>() {
            override fun areItemsTheSame(oldItem: MainData, newItem: MainData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MainData, newItem: MainData): Boolean =
                oldItem.name == newItem.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainListViewHolder {
        return MainListViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MainListViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }
}

class MainListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    companion object {
        fun create(parent: ViewGroup): MainListViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_main, parent, false)
            return MainListViewHolder(view)
        }
    }

    private val tvMainData: TextView = itemView.findViewById(R.id.mainData)

    @SuppressLint("SetTextI18n")
    fun bindTo(item: MainData?) {
        tvMainData.text = "id: ${item?.id}, name: ${item?.name}"
    }
}

data class MainData(val id: Int, val name: String)

