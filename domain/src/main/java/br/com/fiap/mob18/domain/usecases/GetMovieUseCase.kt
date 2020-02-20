package br.com.fiap.mob18.domain.usecases

import br.com.fiap.mob18.domain.model.Movie
import br.com.fiap.mob18.domain.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.internal.schedulers.IoScheduler

class GetMovieUseCase(
        private val repository: MoviesRepository,
        private val ioScheduler: Scheduler
) {
    fun execute(movieId : Long) : Observable<Movie>{
        return repository.getMovie(movieId).observeOn(ioScheduler)
    }

}