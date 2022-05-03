package com.example.moviesappselect.di

import android.content.Context
import com.example.data.net.CloudData
import com.example.data.net.Service
import com.example.data.repository.MapCacheToDomain
import com.example.data.repository.MapCloudToCache
import com.example.data.repository.RepositoryImpl
import com.example.data.repository.ToDispatch
import com.example.data.storage.room.AppRoomDao
import com.example.data.storage.room.AppRoomDatabase
import com.example.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .readTimeout(1, TimeUnit.MINUTES)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Service.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): Service = retrofit.create(Service::class.java)

    @Provides
    @Singleton
    fun provideCloud(service: Service): CloudData = CloudData.Base(service)

    @Provides
    @Singleton
    fun provideDao(@ApplicationContext context: Context): AppRoomDao =
        AppRoomDatabase.getInstance(context = context).getAppRoomDao()

    @Provides
    fun provideDispatchers(): ToDispatch = ToDispatch.Base()

    @Provides
    @Singleton
    fun provideBaseMapCacheToDomain(): MapCacheToDomain =
        MapCacheToDomain.Base()

    @Provides
    @Singleton
    fun provideBaseMapCloudToCache(): MapCloudToCache =
        MapCloudToCache.Base()

    @Provides
    @Singleton
    fun provideRepository(
        appDao: AppRoomDao,
        mapCacheToDomain: MapCacheToDomain,
        mapCloudToCache: MapCloudToCache,
        cloudData: CloudData,
        dispatchers: ToDispatch,
    ): Repository = RepositoryImpl(
        appDao = appDao,
        mapperCacheToDomain = mapCacheToDomain,
        mapperCloudToCache = mapCloudToCache,
        cloudData = cloudData,
        dispatchers = dispatchers
    )
}