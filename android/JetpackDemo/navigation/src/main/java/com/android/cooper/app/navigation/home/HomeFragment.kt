package com.android.cooper.app.navigation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.cooper.app.navigation.R
import com.android.cooper.app.navigation.databinding.FragmentMainBinding
import com.android.cooper.app.navigation.viewBinding

class HomeFragment : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding<FragmentMainBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btNav.setOnClickListener {
            findNavController().navigate(R.id.secondaryFragment)
        }
        //btNav.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.secondaryFragment))
    }
}