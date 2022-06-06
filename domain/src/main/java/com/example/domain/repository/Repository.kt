package com.example.domain.repository

import com.example.domain.models.Result

interface Repository {
    val allMovies: Result
    fun refreshData()
}