package com.example.moviesappselect.model

data class ResultApp(
    val byline: String,
    val critics_pick: Int,
    val date_updated: String,
    val display_title: String,
    val headline: String,
    val link: LinkApp,
    val mpaa_rating: String,
    val multimedia: MultimediaApp,
    val opening_date: String,
    val publication_date: String,
    val summary_short: String
)