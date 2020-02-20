package com.arctouch.codechallenge.base

import androidx.appcompat.app.AppCompatActivity
import br.com.fiap.mob18.data.remote.api.TmdbApi
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity() {
    protected val api: TmdbApi by inject()
    /*
    protected val api: TmdbApi = Retrofit.Builder()
        .baseUrl(TmdbApi.URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(br.com.fiap.mob18.data.remote.api.TmdbApi::class.java)
        */

}
