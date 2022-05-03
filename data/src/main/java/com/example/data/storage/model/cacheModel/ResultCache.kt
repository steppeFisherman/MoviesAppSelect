package com.example.data.storage.model.cacheModel

data class ResultCache(
    val byline: String,
    val critics_pick: Int,
    val date_updated: String,
    val display_title: String,
    val headline: String,
    val link: LinkCache,
    val mpaa_rating: String,
    val multimedia: MultimediaCache,
    val opening_date: String,
    val publication_date: String,
    val summary_short: String
)