package com.example.data.net

import com.example.data.storage.model.cloudModel.MovieCloud
import retrofit2.http.GET

interface Service {

    companion object {
        const val BASE_URL = "https://api.nytimes.com/"
    }

    @GET("svc/movies/v2/reviews/all.json?api-key=RfKDeuqwXGoiamMnWkIjouzeSB7KNAfA")
    suspend fun fetchCloud(): MovieCloud
}
