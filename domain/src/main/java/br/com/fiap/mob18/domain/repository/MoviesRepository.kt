package br.com.fiap.mob18.domain.repository

import br.com.fiap.mob18.domain.model.Movie
import io.reactivex.Observable


interface MoviesRepository {
    fun getUpcoming(currentPage : Long = 0) : Observable<List<Movie>>
    fun getMovie(movieId : Long) : Observable<Movie>
}