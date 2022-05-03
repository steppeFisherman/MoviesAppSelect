package com.example.domain.usecases

import com.example.domain.models.Result
import com.example.domain.repository.Repository
import javax.inject.Inject

interface FetchUseCase {

    fun execute(): Result

    class Base @Inject constructor(private val repository: Repository) : FetchUseCase {
        override fun execute(): Result {
            return repository.allMovies
        }
    }
}