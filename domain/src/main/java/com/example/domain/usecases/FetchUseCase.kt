package com.example.domain.usecases

import com.example.domain.models.Result
import com.example.domain.repository.Repository
import javax.inject.Inject

interface FetchUseCase {

    fun execute(): Result
    fun refreshData()

    class Base @Inject constructor(private val repository: Repository) : FetchUseCase {

        override fun execute(): Result = repository.allMovies

        override fun refreshData() {
            repository.refreshData()
        }
    }
}