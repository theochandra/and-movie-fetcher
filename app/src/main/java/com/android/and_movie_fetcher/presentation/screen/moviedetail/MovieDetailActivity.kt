package com.android.and_movie_fetcher.presentation.screen.moviedetail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.android.and_movie_fetcher.R
import com.android.and_movie_fetcher.databinding.ActivityMovieDetailBinding
import com.android.and_movie_fetcher.presentation.model.MovieVM

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        private const val ARG_PARCEL_MOVIE_VM = "ARG_PARCEL_MOVIE_VM"

        @JvmStatic
        fun newIntent(context: Context, movieVM: MovieVM): Intent {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(ARG_PARCEL_MOVIE_VM, movieVM)
            return intent
        }
    }

    private lateinit var binding: ActivityMovieDetailBinding
    private var movieVM: MovieVM? = null

    private fun getArguments() {
        intent.getParcelableExtra<MovieVM>(ARG_PARCEL_MOVIE_VM)?.let {
            movieVM = it
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArguments()
        binding = DataBindingUtil
            .setContentView(this, R.layout.activity_movie_detail)
        movieVM?.let {
            binding.model = it
        }

        val toolbar = binding.toolbar
        toolbar.setNavigationIcon(R.drawable.ic_white_arrow_back)
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}