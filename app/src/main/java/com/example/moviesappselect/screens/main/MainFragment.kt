package com.example.moviesappselect.screens.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviesappselect.databinding.FragmentMainBinding
import com.example.moviesappselect.screens.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment() : BaseFragment<FragmentMainBinding>() {

    private val vm by viewModels<MainFragmentViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.allMovies.observe(viewLifecycleOwner){
            Log.d("AAA", "allMovies.observe: ${it.size}")
        }
    }
}