package br.com.fiap.mob18.domain.di

import br.com.fiap.mob18.domain.usecases.GetMovieUseCase
import br.com.fiap.mob18.domain.usecases.GetUpcomingUseCase
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module

val useCaseModule = module{
    factory{
        GetMovieUseCase(
                repository = get(),
                ioScheduler = Schedulers.io()
        )
    }

    factory{
        GetUpcomingUseCase(
                repository = get(),
                ioScheduler = Schedulers.io()
        )
    }
}

val domainModule = listOf(useCaseModule)