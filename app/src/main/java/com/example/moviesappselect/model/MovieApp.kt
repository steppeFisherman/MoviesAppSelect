package com.example.moviesappselect.model

data class MovieApp(
    val id: Int,
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<ResultApp>,
    val status: String
)