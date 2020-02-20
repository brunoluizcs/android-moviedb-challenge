package br.com.fiap.mob18.domain.usecases

import br.com.fiap.mob18.domain.model.Movie
import br.com.fiap.mob18.domain.model.UpcomingMoviesResponse
import br.com.fiap.mob18.domain.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single

class GetUpcomingUseCase(
        private val repository : MoviesRepository,
        private val ioScheduler: Scheduler
) {
    fun execute(currentPage : Long = 1) : Single<List<Movie>> {
        return repository.getUpcoming(currentPage).subscribeOn(ioScheduler)
    }
}