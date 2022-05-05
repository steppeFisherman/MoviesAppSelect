package com.example.data.net

import com.example.data.storage.model.cloudModel.MovieCloud
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    companion object {
        const val BASE_URL = "https://api.nytimes.com/"
        const val API_KEY = "RfKDeuqwXGoiamMnWkIjouzeSB7KNAfA"
    }

    @GET("svc/movies/v2/reviews/all.json")
    suspend fun fetchCloud(@Query("api-key") key: String = API_KEY): MovieCloud
}
