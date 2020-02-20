package br.com.fiap.mob18.data.remote.cache

object Cache {

    var genres = listOf<br.com.fiap.mob18.domain.model.Genre>()

    fun cacheGenres(genres: List<br.com.fiap.mob18.domain.model.Genre>) {
        Cache.genres = genres
    }
}
