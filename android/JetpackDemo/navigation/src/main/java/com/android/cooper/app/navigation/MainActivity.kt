package com.android.cooper.app.navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

/**
 * https://developer.android.com/guide/navigation
 *
 * 导航组件由以下三个关键部分组成：
 * 导航图：在一个集中位置包含所有导航相关信息的 XML 资源。
 *        这包括应用内所有单个内容区域（称为目标）以及用户可以通过应用获取的可能路径。
 * NavHost：显示导航图中目标的空白容器。
 *        导航组件包含一个默认 NavHost 实现 [NavHostFragment]，可显示 Fragment 目标。
 * NavController：在 NavHost 中管理应用导航的对象。
 *        当用户在整个应用中移动时，NavController 会安排 NavHost 中目标内容的交换。
 *
 * 注意：Navigation 组件旨在用于具有一个主 Activity 和多个 Fragment 目的地的应用。
 * 主 Activity 与导航图相关联，且包含一个负责根据需要交换目的地的 NavHostFragment。
 * 在具有多个 Activity 目的地的应用中，每个 Activity 均拥有其自己的导航图。
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        host.navController
    }
}