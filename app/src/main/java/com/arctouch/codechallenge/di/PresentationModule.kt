package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.detail.DetailViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module{
    viewModel {
        DetailViewModel(
                movieUseCase = get(),
                uiScheduler = AndroidSchedulers.mainThread()
        )
    }
}