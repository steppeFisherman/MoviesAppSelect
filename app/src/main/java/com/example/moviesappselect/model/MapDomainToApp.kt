package com.example.moviesappselect.model

import com.example.domain.models.LinkDomain
import com.example.domain.models.MovieDomain
import com.example.domain.models.MultimediaDomain
import com.example.domain.models.ResultDomain

interface MapDomainToApp {

    fun mapDomainToAppLink(domain: LinkDomain): LinkApp
    fun mapDomainToAppMovie(domain: MovieDomain): MovieApp
    fun mapDomainToAppMultimedia(domain: MultimediaDomain): MultimediaApp
    fun mapDomainToAppResult(domain: ResultDomain): ResultApp

    class Base : MapDomainToApp {

        override fun mapDomainToAppLink(domain: LinkDomain): LinkApp = LinkApp(
            suggested_link_text = domain.suggested_link_text,
            type = domain.type,
            url = domain.url
        )

        override fun mapDomainToAppMovie(domain: MovieDomain): MovieApp = MovieApp(
            copyright = domain.copyright,
            has_more = domain.has_more,
            num_results = domain.num_results,
            results = listDomainToAppResult(domain.results),
            status = domain.status
        )

        override fun mapDomainToAppMultimedia(domain: MultimediaDomain): MultimediaApp =
            MultimediaApp(
                height = domain.height,
                src = domain.src,
                type = domain.type,
                width = domain.width
            )

        override fun mapDomainToAppResult(domain: ResultDomain): ResultApp = ResultApp(
            byline = domain.byline,
            critics_pick = domain.critics_pick,
            date_updated = domain.date_updated,
            display_title = domain.display_title,
            headline = domain.headline,
            link = mapDomainToAppLink(domain = domain.link),
            mpaa_rating = domain.mpaa_rating,
            multimedia = mapDomainToAppMultimedia(domain = domain.multimedia),
            opening_date = domain.opening_date,
            publication_date = domain.publication_date,
            summary_short = domain.summary_short
        )

        private fun listDomainToAppMovie(list: List<MovieDomain>)
                : List<MovieApp> {
            return list.map { domainMovie ->
                mapDomainToAppMovie(domainMovie)
            }
        }

        private fun listDomainToAppResult(list: List<ResultDomain>)
                : List<ResultApp> {
            return list.map { domainResult ->
                mapDomainToAppResult(domainResult)
            }
        }
    }
}