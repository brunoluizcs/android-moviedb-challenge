package br.com.fiap.mob18.data.di

import br.com.fiap.mob18.data.remote.api.TmdbApi
import br.com.fiap.mob18.data.source.MoviesRemoteDataSource
import br.com.fiap.mob18.data.source.MoviesRemoteDataSourceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module{

    factory { providesOkHttpClient() }
    single {
        createWebService<TmdbApi>(
                okHttpClient = get(),
                url = TmdbApi.URL
        )
    }


    factory<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl(
                api = get()
        )
    }
}

fun providesOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().also { it.level = HttpLoggingInterceptor.Level.BODY })
            .build()
}



inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient,
        url: String
): T {
    return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(T::class.java)
}