package com.example.data.repository

import com.example.data.storage.model.cacheModel.LinkCache
import com.example.data.storage.model.cacheModel.MovieCache
import com.example.data.storage.model.cacheModel.MultimediaCache
import com.example.data.storage.model.cacheModel.ResultCache
import com.example.domain.models.LinkDomain
import com.example.domain.models.MovieDomain
import com.example.domain.models.MultimediaDomain
import com.example.domain.models.ResultDomain

interface MapCacheToDomain {

    fun mapCacheToDomainLink(cache: LinkCache): LinkDomain
    fun mapCacheToDomainMovie(cache: MovieCache): MovieDomain
    fun mapCacheToDomainMultimedia(cache: MultimediaCache): MultimediaDomain
    fun mapCacheToDomainResult(cache: ResultCache): ResultDomain

    class Base : MapCacheToDomain {

        override fun mapCacheToDomainLink(cache: LinkCache): LinkDomain = LinkDomain(
            suggested_link_text = cache.suggested_link_text,
            type = cache.type,
            url = cache.url
        )

        override fun mapCacheToDomainMovie(cache: MovieCache): MovieDomain = MovieDomain(
            copyright = cache.copyright,
            has_more = cache.has_more,
            num_results = cache.num_results,
            results = listCacheToDomainResult(cache.results),
            status = cache.status
        )

        override fun mapCacheToDomainMultimedia(cache: MultimediaCache): MultimediaDomain =
            MultimediaDomain(
                height = cache.height,
                src = cache.src,
                type = cache.type,
                width = cache.width
            )

        override fun mapCacheToDomainResult(cache: ResultCache): ResultDomain = ResultDomain(
            byline = cache.byline,
            critics_pick = cache.critics_pick,
            date_updated = cache.date_updated,
            display_title = cache.display_title,
            headline = cache.headline,
            link = mapCacheToDomainLink(cache = cache.link),
            mpaa_rating = cache.mpaa_rating,
            multimedia = mapCacheToDomainMultimedia(cache = cache.multimedia),
            opening_date = cache.opening_date,
            publication_date = cache.publication_date,
            summary_short = cache.summary_short
        )

        private fun listCacheToDomainMovie(list: List<MovieCache>)
                : List<MovieDomain> {
            return list.map { cacheMovie ->
                mapCacheToDomainMovie(cacheMovie)
            }
        }

        private fun listCacheToDomainResult(list: List<ResultCache>)
                : List<ResultDomain> {
            return list.map { cacheResult ->
                mapCacheToDomainResult(cacheResult)
            }
        }
    }
}