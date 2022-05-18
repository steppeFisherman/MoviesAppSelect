package com.example.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.data.net.CloudData
import com.example.data.storage.room.AppRoomDao
import com.example.domain.models.ErrorType
import com.example.domain.models.MovieDomain
import com.example.domain.models.Result
import com.example.domain.repository.Repository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import java.net.UnknownHostException
import javax.inject.Singleton

@Singleton
class RepositoryImpl(
    private val appDao: AppRoomDao,
    private val mapperCacheToDomain: MapCacheToDomain,
    private val mapperCloudToCache: MapCloudToCache,
    private val cloudData: CloudData,
    private val dispatchers: ToDispatch,
) : Repository {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ -> }
    private val scope = CoroutineScope(Job() + exceptionHandler)

    override val allMovies: Result
        get() = try {
            Result.Success(fetchFromCloud())
        } catch (e: Exception) {
            Result.Fail(
                when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is NullPointerException -> ErrorType.NULL_POINTER_EXCEPTION
                    else -> ErrorType.GENERIC_ERROR
                }
            )
        }

    /**
    Always fetch data from cache after initial retrieve
     */
//    private fun fetchFromCloud(): MutableLiveData<List<MovieDomain>> {
//        val item = MutableLiveData<List<MovieDomain>>()
//        dispatchers.launchIO(scope = scope) {
//            val cacheList = appDao.fetchAllMoviesBySuspend()
//            if (cacheList.isNullOrEmpty()) {
//                val cloud = cloudData.fetchCloud()
//                val cache = mapperCloudToCache.mapCloudToCacheMovie(cloud)
//                appDao.insertMovie(cache)
//                dispatchers.launchUI(this) {
//                    item.value = listOf(mapperCacheToDomain.mapCacheToDomainMovie(cache))
//                }
//            } else {
//                dispatchers.launchUI(this) {
//                    item.value = cacheList.map { dataCache ->
//                        mapperCacheToDomain.mapCacheToDomainMovie(dataCache)
//                    }
//                }
//            }
//        }
//        return item
//    }

    /**
    Always fetch new data from cloud, in case of error retrieves from cache
     */
    private fun fetchFromCloud(): MutableLiveData<List<MovieDomain>> {
        val item = MutableLiveData<List<MovieDomain>>()
        dispatchers.launchIO(scope = scope) {
            val cloud = cloudData.fetchCloud()
            val cache = mapperCloudToCache.mapCloudToCacheMovie(cloud)
            appDao.insertMovie(cache)
            val newData = mapperCacheToDomain.mapCacheToDomainMovie(cache)
            if (!cloud.results.isNullOrEmpty()) {
                dispatchers.launchUI(this) {
                    item.value = listOf(newData)
                }
            } else {
                val cacheList = appDao.fetchAllMoviesBySuspend()
                val oldData = cacheList.map { mapperCacheToDomain.mapCacheToDomainMovie(it) }
                dispatchers.launchUI(this) {
                    item.value = oldData
                }
            }
        }
        return item
    }
}