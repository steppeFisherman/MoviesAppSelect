package com.example.data.storage.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.storage.model.cacheModel.MovieCache

@Dao
interface AppRoomDao {

    @Query("SELECT * FROM movie_table")
    fun fetchAllMovies(): LiveData<List<MovieCache>>

    @Query("SELECT * FROM movie_table")
    suspend fun fetchAllMoviesBySuspend(): List<MovieCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieCache: MovieCache)
}