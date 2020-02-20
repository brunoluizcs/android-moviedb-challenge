package com.arctouch.codechallenge.util

import br.com.fiap.mob18.data.remote.api.TmdbApi

private val POSTER_URL = "https://image.tmdb.org/t/p/w154"
private val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

class MovieImageUrlBuilder {

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?api_key=" + br.com.fiap.mob18.data.remote.api.TmdbApi.API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?api_key=" + br.com.fiap.mob18.data.remote.api.TmdbApi.API_KEY
    }
}
