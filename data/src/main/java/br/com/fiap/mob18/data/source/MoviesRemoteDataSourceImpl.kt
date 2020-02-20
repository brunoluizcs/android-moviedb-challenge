package br.com.fiap.mob18.data.source

import br.com.fiap.mob18.data.remote.api.TmdbApi
import br.com.fiap.mob18.data.remote.cache.Cache
import br.com.fiap.mob18.domain.model.Genre
import br.com.fiap.mob18.domain.model.Movie
import io.reactivex.Observable

class MoviesRemoteDataSourceImpl(
        private val api : TmdbApi
) : MoviesRemoteDataSource{


    override fun getUpcoming(currentPage: Long): Observable<List<Movie>> {
        return getGenres()
            .flatMap {listGenre ->
                api.upcomingMovies(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE, currentPage, TmdbApi.DEFAULT_REGION).map {
                    val moviesWithGenres = it.results.map { movie ->
                        movie.copy(genres = listGenre.filter { movie.genreIds?.contains(it.id) == true })
                    }
                    moviesWithGenres
                }
            }
    }

    override fun getMovie(movieId: Long): Observable<Movie> {
        return getGenres()
                .flatMap {listGenre ->
                    val moviesWithGenres = api.movie(movieId, TmdbApi.API_KEY,TmdbApi.DEFAULT_LANGUAGE).map {movie ->
                        movie.copy(genres = listGenre.filter { movie.genreIds?.contains(it.id) == true })
                    }
                    moviesWithGenres
                }
    }

    private fun getGenres() : Observable<List<Genre>>{
        return if  (Cache.genres.isNotEmpty()){
            Observable.just(Cache.genres)
        }else{
            api.genres(TmdbApi.API_KEY, TmdbApi.DEFAULT_LANGUAGE)
                    .flatMap {response ->
                        Cache.cacheGenres(response.genres)
                        Observable.just(response.genres)
                    }
        }
    }
}