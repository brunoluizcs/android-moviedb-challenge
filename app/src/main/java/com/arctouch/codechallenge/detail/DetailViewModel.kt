package com.arctouch.codechallenge.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.mob18.domain.model.Movie
import br.com.fiap.mob18.domain.usecases.GetMovieUseCase
import com.arctouch.codechallenge.viewmodel.BaseViewModel
import com.arctouch.codechallenge.viewmodel.StateMachineSingle
import com.arctouch.codechallenge.viewmodel.ViewState
import io.reactivex.Scheduler
import io.reactivex.rxkotlin.plusAssign

class DetailViewModel(
        private val movieUseCase: GetMovieUseCase,
        private val uiScheduler : Scheduler
) : BaseViewModel() {

    private val _movieData = MutableLiveData<ViewState<Movie>>()

    val movieData : LiveData<ViewState<Movie>> = _movieData

    fun getMovie(movieId : Long){
        disposables += movieUseCase.execute(movieId).observeOn(uiScheduler)
                .observeOn(uiScheduler)
                .compose(StateMachineSingle())
                .subscribe({
                    _movieData.postValue(it)
                },{
                    //Not Necessary view state handle it
                })
    }

}