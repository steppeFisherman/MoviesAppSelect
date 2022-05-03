package com.example.data.storage.model.cloudModel

import com.google.gson.annotations.SerializedName

data class LinkCloud(
    @SerializedName("suggested_link_text")
    val suggested_link_text: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)