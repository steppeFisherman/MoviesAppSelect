package com.example.domain.models

data class ResultDomain(
    val byline: String,
    val critics_pick: Int,
    val date_updated: String,
    val display_title: String,
    val headline: String,
    val link: LinkDomain,
    val mpaa_rating: String,
    val multimedia: MultimediaDomain,
    val opening_date: String,
    val publication_date: String,
    val summary_short: String
)