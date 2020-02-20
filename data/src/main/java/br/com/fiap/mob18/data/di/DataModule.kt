package br.com.fiap.mob18.data.di

import br.com.fiap.mob18.data.repository.MoviesRepositoryImpl
import br.com.fiap.mob18.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module{
    factory<MoviesRepository>{
        MoviesRepositoryImpl(
                moviesRemoteDataSource = get()
        )
    }
}

val dataModule = repositoryModule + remoteDataSourceModule