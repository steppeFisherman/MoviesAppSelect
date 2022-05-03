package com.example.data.storage.model.cloudModel

import com.google.gson.annotations.SerializedName

data class MultimediaCloud(
    @SerializedName("height")
    val height: Int,
    @SerializedName("src")
    val src: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("width")
    val width: Int
)