package com.arctouch.codechallenge.di

import com.arctouch.codechallenge.detail.DetailViewModel
import com.arctouch.codechallenge.home.HomeAdapter
import com.arctouch.codechallenge.home.HomeViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailModule = module {
    viewModel {
        DetailViewModel(
                movieUseCase = get(),
                uiScheduler = AndroidSchedulers.mainThread()
        )
    }
}

val homeModule = module{
    viewModel {
        HomeViewModel(
                getUpcomingUseCase = get(),
                uiScheduler = AndroidSchedulers.mainThread()
        )
    }

    factory { HomeAdapter() }
}

val presentationModule = detailModule + homeModule