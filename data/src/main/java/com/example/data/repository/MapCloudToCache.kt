package com.example.data.repository

import com.example.data.storage.model.cacheModel.LinkCache
import com.example.data.storage.model.cacheModel.MovieCache
import com.example.data.storage.model.cacheModel.MultimediaCache
import com.example.data.storage.model.cacheModel.ResultCache
import com.example.data.storage.model.cloudModel.LinkCloud
import com.example.data.storage.model.cloudModel.MovieCloud
import com.example.data.storage.model.cloudModel.MultimediaCloud
import com.example.data.storage.model.cloudModel.ResultCloud

interface MapCloudToCache {

    fun mapCloudToCacheLink(cloud: LinkCloud): LinkCache
    fun mapCloudToCacheMovie(cloud: MovieCloud): MovieCache
    fun mapCloudToCacheMultimedia(cloud: MultimediaCloud): MultimediaCache
    fun mapCloudToCacheResult(cloud: ResultCloud): ResultCache

    class Base : MapCloudToCache {

        override fun mapCloudToCacheLink(cloud: LinkCloud): LinkCache = LinkCache(
            suggested_link_text = cloud.suggested_link_text ?: "",
            type = cloud.type ?: "",
            url = cloud.url ?: ""
        )

        override fun mapCloudToCacheMovie(cloud: MovieCloud): MovieCache = MovieCache(
            copyright = cloud.copyright ?: "",
            has_more = cloud.has_more ?: false,
            num_results = cloud.num_results ?: 0,
            results = listCloudToCacheResult(cloud.results ?: emptyList()),
            status = cloud.status ?: "",
            id = 0
        )

        override fun mapCloudToCacheMultimedia(cloud: MultimediaCloud): MultimediaCache =
            MultimediaCache(
                height = cloud.height ?: 0,
                src = cloud.src ?: "",
                type = cloud.type ?: "",
                width = cloud.width ?: 0
            )

        override fun mapCloudToCacheResult(cloud: ResultCloud): ResultCache = ResultCache(
            byline = cloud.byline ?: "",
            critics_pick = cloud.critics_pick ?: 0,
            date_updated = cloud.date_updated ?: "",
            display_title = cloud.display_title ?: "",
            headline = cloud.headline ?: "",
            link = mapCloudToCacheLink(cloud = cloud.link ?: emptyLinkCloud),
            mpaa_rating = cloud.mpaa_rating ?: "",
            multimedia = mapCloudToCacheMultimedia(
                cloud = cloud.multimedia ?: emptyMultimediaCloud
            ),
            opening_date = cloud.opening_date ?: "",
            publication_date = cloud.publication_date ?: "",
            summary_short = cloud.summary_short ?: ""
        )

        private fun listCloudToCacheMovie(list: List<MovieCloud>)
                : List<MovieCache> {
            return list.map { cloudMovie ->
                mapCloudToCacheMovie(cloudMovie)
            }
        }

        private fun listCloudToCacheResult(list: List<ResultCloud>)
                : List<ResultCache> {
            return list.map { cloudResult ->
                mapCloudToCacheResult(cloudResult)
            }
        }

        private val emptyLinkCloud = LinkCloud("", "", "")
        private val emptyMultimediaCloud = MultimediaCloud(0, "", "", 0)
    }
}