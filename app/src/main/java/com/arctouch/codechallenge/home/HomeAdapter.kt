package com.arctouch.codechallenge.home


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arctouch.codechallenge.R
import br.com.fiap.mob18.domain.model.Movie
import com.arctouch.codechallenge.util.MovieImageUrlBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.movie_item.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    private var movies = mutableListOf<Movie>()

    fun setData(data: List<Movie>){
        this.movies.clear()
        this.movies.addAll(data)
        notifyDataSetChanged()
    }

    interface OnMovieClickLister{
        fun onMovieClick(movie: Movie)
    }


    var onMovieClickLister : OnMovieClickLister? = null



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(movie: Movie) {
            itemView.setOnClickListener {
                onMovieClickLister?.onMovieClick(movie)
            }
            itemView.titleTextView.text = movie.title
            itemView.genresTextView.text = movie.genres?.joinToString(separator = ", ") { it.name }
            itemView.releaseDateTextView.text = movie.releaseDate

            Glide.with(itemView)
                .load(movie.posterPath?.let { MovieImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.ic_image_placeholder))
                .into(itemView.posterImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movies[position])
}
