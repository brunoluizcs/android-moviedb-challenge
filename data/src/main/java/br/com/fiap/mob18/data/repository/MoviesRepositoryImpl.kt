package br.com.fiap.mob18.data.repository

import br.com.fiap.mob18.data.source.MoviesRemoteDataSource
import br.com.fiap.mob18.domain.model.Movie
import br.com.fiap.mob18.domain.repository.MoviesRepository
import io.reactivex.Observable

class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) : MoviesRepository {


    override fun getUpcoming(currentPage: Long): Observable<List<Movie>> {
        return moviesRemoteDataSource.getUpcoming(currentPage = currentPage)
    }

    override fun getMovie(movieId: Long): Observable<Movie> {
        return moviesRemoteDataSource.getMovie(movieId)
    }

}