package com.arctouch.codechallenge.home


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.mob18.domain.model.Movie
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.HomeActivityBinding
import com.arctouch.codechallenge.detail.DetailActivity
import com.arctouch.codechallenge.extensions.visible
import com.arctouch.codechallenge.viewmodel.ViewState
import org.koin.android.ext.android.inject


class HomeActivity : AppCompatActivity() {

    private lateinit var binding : HomeActivityBinding

    private val homeAdapter : HomeAdapter by inject()
    private val homeViewModel : HomeViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.home_activity)

        initializeObservers()

        setupRecyclerView()


        if (savedInstanceState == null)
            homeViewModel.requestMovie()
    }

    override fun onDestroy() {
        removeObservers()
        super.onDestroy()
    }

    private fun setupRecyclerView(){
        homeAdapter.onMovieClickLister = object : HomeAdapter.OnMovieClickLister{
            override fun onMovieClick(movie: Movie) {
                val i = DetailActivity.intentWithMovieId(this@HomeActivity,movie.id.toLong())
                startActivity(i)
            }
        }

        binding.recyclerView.addOnScrollListener(
                object: RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)
                        if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                            homeViewModel.requestMovie()
                        }
                    }
                }
        )

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = homeAdapter
    }

    private fun initializeObservers() {
        homeViewModel.moviesData.observe(this, Observer {
            when(it){
                is ViewState.Success -> onSuccess(it.data)
                is ViewState.Failed -> onFailed(it.throwable)
                is ViewState.Loading -> setVisibilities(showProgressBar = true)
            }
        })
    }

    private fun removeObservers(){
        homeViewModel.moviesData.removeObservers(this)
    }

    private fun onSuccess(data: List<Movie>) {
        setVisibilities(showData = true)
        homeAdapter.setData(data)
    }

    private fun onFailed(throwable: Throwable){
        setVisibilities(showMessage = true, showData = true)
        binding.messageTextView.text = getString(R.string.error_server_request,throwable.message)
    }

    private fun setVisibilities(
            showProgressBar: Boolean = false,
            showData: Boolean = false,
            showMessage: Boolean = false
    ) {
        binding.progressBar.visible(showProgressBar)
        binding.recyclerView.visible(showData)
        binding.messageTextView.visible(showMessage)
    }
}
