package com.arctouch.codechallenge.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import br.com.fiap.mob18.domain.model.Movie
import com.arctouch.codechallenge.R
import com.arctouch.codechallenge.databinding.ActivityDetailBinding
import com.arctouch.codechallenge.extensions.visible
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.arctouch.codechallenge.viewmodel.ViewState
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel : DetailViewModel by viewModel()

    companion object{
        fun intentWithMovieId(context : Context, movieId : Long) = Intent(context,DetailActivity::class.java).also {
            it.putExtra("movieId",movieId)
        }
    }


    private lateinit var binding : ActivityDetailBinding

    private var movieId : Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_detail)
        initializeObservers()

        movieId = intent.extras?.getLong("movieId")

        movieId?.let { detailViewModel.getMovie(it) }
    }

    override fun onDestroy() {
        removeObservers()
        super.onDestroy()
    }


    private fun initializeObservers(){
        detailViewModel.movieData.observe(this, Observer {
            when(it){
                is ViewState.Success -> {onSuccess(it.data)}
                is ViewState.Failed -> { onFailed(it.throwable)}
                is ViewState.Loading -> {setVisibilities(showProgressBar = true)}
            }
        })
    }



    private fun removeObservers(){
        detailViewModel.movieData.removeObservers(this)
    }


    private fun onSuccess(movie: Movie) {
        setVisibilities(showData = true)

        binding.incLayoutDetailContent.nameTextView.text = movie.title
        binding.incLayoutDetailContent.genresTextView.text = movie.genres?.joinToString(separator = " | ") { it.name }
        binding.incLayoutDetailContent.releaseDateTextView.text = movie.releaseDate
        binding.incLayoutDetailContent.overviewTextView.text = movie.overview

        Glide.with(this@DetailActivity)
                .load(movie.posterPath?.let { MovieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(binding.incLayoutDetailContent.posterImageView)

        Glide.with(this@DetailActivity)
                .load(movie.backdropPath?.let { MovieImageUrlBuilder.buildBackdropUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(binding.incLayoutDetailContent.backdropImageView)
    }

    private fun onFailed(throwable: Throwable){
        setVisibilities(showMessage = true)
        binding.messageTextView.text = getString(R.string.error_server_request,throwable.message)
    }


    private fun setVisibilities(
            showProgressBar: Boolean = false,
            showData: Boolean = false,
            showMessage: Boolean = false
    ) {
        binding.incLayoutDetailContent.root.visible(showData)
        binding.progressBar.visible(showProgressBar)
        binding.messageTextView.visible(showMessage)
    }




}
