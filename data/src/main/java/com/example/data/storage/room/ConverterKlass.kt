package com.example.data.storage.room

import androidx.room.TypeConverter
import com.example.data.storage.model.cacheModel.LinkCache
import com.example.data.storage.model.cacheModel.MultimediaCache
import com.example.data.storage.model.cacheModel.ResultCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ConverterKlass {

    @TypeConverter
    fun fromResultCacheList(value: List<ResultCache>): String {
        val gson = Gson()
        val type = object : TypeToken<List<ResultCache>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toResultCacheList(value: String): List<ResultCache> {
        val gson = Gson()
        val type = object : TypeToken<List<ResultCache>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromLinkCache(value: LinkCache): String {
        val gson = Gson()
        val type = object : TypeToken<LinkCache>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toLinkCache(value: String): LinkCache {
        val gson = Gson()
        val type = object : TypeToken<LinkCache>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromMultimediaCache(value: MultimediaCache): String {
        val gson = Gson()
        val type = object : TypeToken<MultimediaCache>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toMultimediaCache(value: String): MultimediaCache {
        val gson = Gson()
        val type = object : TypeToken<MultimediaCache>() {}.type
        return gson.fromJson(value, type)
    }
}