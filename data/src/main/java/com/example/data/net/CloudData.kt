package com.example.data.net

import com.example.data.storage.model.cloudModel.MovieCloud
import javax.inject.Inject

interface CloudData {

    suspend fun fetchCloud(): MovieCloud

    class Base @Inject constructor(private val service: Service) : CloudData {
        override suspend fun fetchCloud(): MovieCloud = service.fetchCloud()
    }
}