package com.example.data.net

import com.example.data.storage.model.cloudModel.MovieCloud
import retrofit2.Response
import javax.inject.Inject

interface CloudData {

    suspend fun fetchCloud(): Response<MovieCloud>

    class Base @Inject constructor(private val service: Service) : CloudData {
        override suspend fun fetchCloud(): Response<MovieCloud> = service.fetchCloud()
    }
}