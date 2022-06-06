package com.example.moviesappselect.screens.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moviesappselect.R
import com.example.moviesappselect.adapters.MainFragmentAdapter
import com.example.moviesappselect.databinding.FragmentMainBinding
import com.example.moviesappselect.screens.BaseFragment
import com.example.moviesappselect.utils.LoadImage
import com.example.moviesappselect.utils.showSnackLong
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val vm by viewModels<MainFragmentViewModel>()

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.allMovies.observe(viewLifecycleOwner) { listMovieApp ->
            val adapter = MainFragmentAdapter(LoadImage.Base())
            mBinding.mainFragmentRv.adapter = adapter
            if (listMovieApp.isNotEmpty()) adapter.setData(listMovieApp[0].results)
        }

        vm.error.observe(viewLifecycleOwner) {
            when (it.ordinal) {
                0 -> view.showSnackLong(R.string.no_connection_message)
                1 -> view.showSnackLong(R.string.null_pointer_exception)
                2 -> view.showSnackLong(R.string.something_went_wrong)
            }
        }
    }
}