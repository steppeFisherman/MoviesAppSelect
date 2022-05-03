package com.example.data.storage.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.storage.model.cacheModel.MovieCache

@Database(entities = [MovieCache::class], version = 1, exportSchema = false)
@TypeConverters(ConverterKlass::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getAppRoomDao(): AppRoomDao

    companion object {
        @Volatile
        private var database: AppRoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context,
                    AppRoomDatabase::class.java,
                    "database"
                ).build()
                database as AppRoomDatabase
            } else database as AppRoomDatabase
        }
    }
}