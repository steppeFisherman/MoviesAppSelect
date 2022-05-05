package com.example.moviesappselect.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.moviesappselect.R
import com.example.moviesappselect.databinding.FragmentSplashBinding
import com.example.moviesappselect.screens.BaseFragment
import com.example.moviesappselect.utils.showSnackIndefinite

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkIfUserIsLoggedIn(true)
    }

    private fun checkIfUserIsLoggedIn(loggedIn: Boolean) {
        if (loggedIn) {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        } else {
            view?.showSnackIndefinite(R.string.please_log_in)
        }
    }
}