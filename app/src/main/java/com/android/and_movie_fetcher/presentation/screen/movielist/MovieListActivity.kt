package com.android.and_movie_fetcher.presentation.screen.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.and_movie_fetcher.R
import com.android.and_movie_fetcher.databinding.ActivityMovieListBinding
import com.android.and_movie_fetcher.di.Injector
import javax.inject.Inject

class MovieListActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: MovieListViewModelFactory

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var viewModel: MovieListViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_movie_list)
        (application as Injector).createMovieListSubComponent()
            .inject(this)
        viewModel = ViewModelProvider(this, factory)
            .get(MovieListViewModel::class.java)
        binding.viewModel = viewModel

        initRecyclerView()
        observeMovieVmList()
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(
                this@MovieListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
        }
    }

    private fun observeMovieVmList() {
        viewModel.movieVmList.observe(this, { movieVmList ->
            movieAdapter.setMovieVmList(movieVmList)
        })
    }

}