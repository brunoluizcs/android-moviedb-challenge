package br.com.fiap.mob18.data.source

import br.com.fiap.mob18.domain.model.Movie
import io.reactivex.Observable

interface MoviesRemoteDataSource{

    fun getUpcoming(currentPage : Long) : Observable<List<Movie>>
    fun getMovie(movieId : Long) : Observable<Movie>

}