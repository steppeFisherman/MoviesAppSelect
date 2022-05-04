package com.example.moviesappselect.screens.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.domain.models.ErrorType
import com.example.domain.models.Result
import com.example.domain.usecases.FetchUseCase
import com.example.moviesappselect.model.MapDomainToApp
import com.example.moviesappselect.model.MovieApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    fetchItemsUseCase: FetchUseCase,
    private val mapper: MapDomainToApp,
) : ViewModel() {

    private var mAllMovies = MutableLiveData<List<MovieApp>>()
    private var mError = MutableLiveData<ErrorType>()
    val allMovies: LiveData<List<MovieApp>>
        get() = mAllMovies
    val error: LiveData<ErrorType>
        get() = mError

    init {
        when (val result = fetchItemsUseCase.execute()) {
            is Result.Success -> {
                Log.d("AAA", "init: ${result.movieDomain.value?.size}")
                mAllMovies = result.movieDomain.map { list ->
                    list.map { mapper.mapDomainToAppMovie(it) }
                } as MutableLiveData<List<MovieApp>>
            }
            is Result.Fail -> mError.value = result.errorType
        }
    }
}