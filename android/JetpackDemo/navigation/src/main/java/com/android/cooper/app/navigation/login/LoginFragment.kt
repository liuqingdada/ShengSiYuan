package com.android.cooper.app.navigation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.android.cooper.app.navigation.R
import com.android.cooper.app.navigation.databinding.FragmentLoginBinding
import com.android.cooper.app.navigation.viewBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private val binding by viewBinding<FragmentLoginBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btSignIn.setOnClickListener {
            findNavController().navigate(R.id.action_global_mainFragment)
        }
    }
}