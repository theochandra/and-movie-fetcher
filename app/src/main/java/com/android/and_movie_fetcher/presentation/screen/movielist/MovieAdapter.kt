package com.android.and_movie_fetcher.presentation.screen.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.and_movie_fetcher.databinding.ItemMovieBinding
import com.android.and_movie_fetcher.presentation.model.MovieVM

class MovieAdapter(
    private val onItemClickListener: (MovieVM) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieVmList = ArrayList<MovieVM>()

    fun setMovieVmList(movieVMs: List<MovieVM>) {
        movieVmList.addAll(movieVMs)
        notifyItemInserted(movieVmList.size - 1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieVmList[position], onItemClickListener)
    }

    override fun getItemCount(): Int = movieVmList.size

    class MovieViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movieVM: MovieVM, onItemClickListener: (MovieVM) -> Unit) {
            binding.model = movieVM
            binding.root.setOnClickListener {
                onItemClickListener(movieVM)
            }
        }

    }

}