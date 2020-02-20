package br.com.fiap.mob18.data.source

import br.com.fiap.mob18.domain.model.Movie
import io.reactivex.Single

interface MoviesRemoteDataSource{

    fun getUpcoming(currentPage : Long) : Single<List<Movie>>
    fun getMovie(movieId : Long) : Single<Movie>

}