package com.example.data.storage.model.cacheModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieCache(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<ResultCache>,
    val status: String
)