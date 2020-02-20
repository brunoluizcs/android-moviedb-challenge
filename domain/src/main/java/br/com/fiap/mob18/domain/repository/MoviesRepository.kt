package br.com.fiap.mob18.domain.repository

import br.com.fiap.mob18.domain.model.Movie
import io.reactivex.Single


interface MoviesRepository {
    fun getUpcoming(currentPage : Long = 0) : Single<List<Movie>>
    fun getMovie(movieId : Long) : Single<Movie>
}