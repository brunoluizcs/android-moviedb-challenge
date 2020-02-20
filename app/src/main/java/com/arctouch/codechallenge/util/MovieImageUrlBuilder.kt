package com.arctouch.codechallenge.util

import br.com.fiap.mob18.data.remote.api.TmdbApi

private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

object MovieImageUrlBuilder {

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?api_key=" + TmdbApi.API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?api_key=" + TmdbApi.API_KEY
    }
}
