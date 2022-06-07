package com.example.data.storage.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.data.storage.model.cacheModel.MovieCache

@Dao
interface AppRoomDao {

    @Query("SELECT * FROM movie_table")
    fun fetchAllMovies(): LiveData<List<MovieCache>>

    @Query("SELECT * FROM movie_table")
    suspend fun fetchAllMoviesBySuspend(): List<MovieCache>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieCache: MovieCache)

    @Query("DELETE FROM movie_table")
    suspend fun deleteMovie()
}