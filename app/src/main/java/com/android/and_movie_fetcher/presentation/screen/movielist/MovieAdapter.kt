package com.android.and_movie_fetcher.presentation.screen.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.and_movie_fetcher.databinding.ItemMovieBinding
import com.android.and_movie_fetcher.presentation.model.MovieVM

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieVmList = ArrayList<MovieVM>()

    fun setMovieVmList(movieVMs: List<MovieVM>) {
        movieVmList.addAll(movieVMs)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movieVM = movieVmList[position]
        holder.binding.model = movieVM
    }

    override fun getItemCount(): Int = movieVmList.size

    class MovieViewHolder(
        val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root)

}