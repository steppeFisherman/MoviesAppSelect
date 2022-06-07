package com.example.domain.models

import androidx.lifecycle.LiveData

sealed class Result {
    data class Success(val movieDomain: LiveData<List<MovieDomain>>) : Result()
    data class Loading(val data: Boolean = true) : Result()
    data class Fail(val errorType: ErrorType) : Result()
}
