package com.example.data.storage.model.cloudModel

import com.google.gson.annotations.SerializedName

data class MovieCloud(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("has_more")
    val has_more: Boolean,
    @SerializedName("num_results")
    val num_results: Int,
    @SerializedName("results")
    val results: List<ResultCloud>,
    @SerializedName("status")
    val status: String
)