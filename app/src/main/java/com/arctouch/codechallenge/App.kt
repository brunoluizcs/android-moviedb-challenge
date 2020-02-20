package com.arctouch.codechallenge

import android.app.Application
import br.com.fiap.mob18.data.di.dataModule
import br.com.fiap.mob18.domain.di.domainModule
import com.arctouch.codechallenge.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App  : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)

            modules(domainModule + dataModule + listOf(presentationModule))
        }
    }
}