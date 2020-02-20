package com.arctouch.codechallenge.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.mob18.domain.model.Movie
import br.com.fiap.mob18.domain.usecases.GetUpcomingUseCase
import com.arctouch.codechallenge.viewmodel.BaseViewModel
import com.arctouch.codechallenge.viewmodel.StateMachineSingle
import com.arctouch.codechallenge.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class HomeViewModel(
        private val getUpcomingUseCase: GetUpcomingUseCase,
        private val uiScheduler: Scheduler
) : BaseViewModel() {

    private var _page = 1L
    private val _movies = mutableListOf<Movie>()
    private var isLoading = false

    private val _moviesData = MutableLiveData<ViewState<List<Movie>>>().also {
        requestMovie()
    }

    val moviesData : LiveData<ViewState<List<Movie>>> = _moviesData


    fun requestMovie(){
        if (!isLoading){
            disposables += getUpcomingUseCase.execute(_page).observeOn(uiScheduler)
                    .doOnSubscribe { isLoading = true }
                    .compose(StateMachineSingle())
                    .subscribe({
                        when(it){
                            is ViewState.Success -> {
                                _page += 1
                                _movies.addAll(it.data)
                                val paggedResult = ViewState.Success<List<Movie>>(_movies)
                                _moviesData.postValue(paggedResult)
                            }
                            else -> _moviesData.postValue(it)
                        }
                        isLoading = false
                    },{
                        //Not necessary ViewState handle it
                    })
        }
    }



}