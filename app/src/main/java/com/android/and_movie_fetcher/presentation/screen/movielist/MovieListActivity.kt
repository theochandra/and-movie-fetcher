package com.android.and_movie_fetcher.presentation.screen.movielist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var scrollListener: InfiniteScrollListener

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
        observeIsStillLoading()
        observeIsLastPage()
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        scrollListener = InfiniteScrollListener {
            getMoreMovieList()
        }
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(
                this@MovieListActivity, LinearLayoutManager.VERTICAL, false)
            adapter = movieAdapter
            addOnScrollListener(scrollListener)
        }
    }

    private fun observeMovieVmList() {
        viewModel.movieVmList.observe(this, { movieVmList ->
            movieAdapter.setMovieVmList(movieVmList)
        })
    }

    private fun observeIsStillLoading() {
        viewModel.isLoading.observe(this, { isLoading ->
            scrollListener.isLoading = isLoading
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun observeIsLastPage() {
        viewModel.isLastPage.observe(this, { isLastPage ->
            scrollListener.isLastPage = isLastPage
        })
    }

    private fun getMoreMovieList() {
        viewModel.getMovieList()
    }

}