package br.com.fiap.mob18.domain.di

import br.com.fiap.mob18.domain.usecases.GetMovieUseCase
import br.com.fiap.mob18.domain.usecases.GetUpComingUseCase
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
        GetUpComingUseCase(
                repository = get(),
                ioScheduler = Schedulers.io()
        )
    }
}

val domainModule = useCaseModule