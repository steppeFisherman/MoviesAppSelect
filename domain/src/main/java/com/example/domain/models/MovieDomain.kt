package com.example.domain.models

data class MovieDomain(
    val id: Int,
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<ResultDomain>,
    val status: String
)