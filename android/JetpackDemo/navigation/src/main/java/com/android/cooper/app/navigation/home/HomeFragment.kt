package com.android.cooper.app.navigation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.common.utils.LogUtil
import com.android.cooper.app.navigation.R
import com.android.cooper.app.navigation.databinding.FragmentMainBinding
import com.android.cooper.app.navigation.viewBinding
import com.android.lib.datastore.DsManager

class HomeFragment : Fragment(R.layout.fragment_main) {
    companion object {
        private const val TAG = "HomeFragment"
        private const val KEY = "home"
    }

    private val binding by viewBinding<FragmentMainBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btNav.setOnClickListener {
            findNavController().navigate(R.id.secondaryFragment)
        }
        //btNav.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.secondaryFragment))

        DsManager.delegate().putInt(TAG, KEY, 100)
        LogUtil.d(TAG, "${DsManager.delegate().getInt(TAG, KEY, -1)}")
    }
}