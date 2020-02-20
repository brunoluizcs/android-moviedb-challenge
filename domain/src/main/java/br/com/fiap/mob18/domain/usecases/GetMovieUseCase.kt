package br.com.fiap.mob18.domain.usecases

import br.com.fiap.mob18.domain.model.Movie
import br.com.fiap.mob18.domain.repository.MoviesRepository
import io.reactivex.Scheduler
import io.reactivex.Single

class GetMovieUseCase(
        private val repository: MoviesRepository,
        private val ioScheduler: Scheduler
) {
    fun execute(movieId : Long) : Single<Movie> {
        return repository.getMovie(movieId).subscribeOn(ioScheduler)
    }

}