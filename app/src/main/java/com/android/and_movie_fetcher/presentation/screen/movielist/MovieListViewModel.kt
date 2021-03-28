package com.android.and_movie_fetcher.presentation.screen.movielist

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.and_movie_fetcher.BuildConfig
import com.android.and_movie_fetcher.presentation.mapper.MovieVmMapper
import com.android.and_movie_fetcher.presentation.model.MovieVM
import com.android.domain.Result
import com.android.domain.usecase.GetMovieListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val useCase: GetMovieListUseCase,
    private val mapper: MovieVmMapper
) : ViewModel() {

    private val _movieVmList = MutableLiveData<List<MovieVM>>()
    val movieVmList: LiveData<List<MovieVM>>
        get() = _movieVmList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() =  _isLoading

    private val _isLastPage = MutableLiveData<Boolean>()
    val isLastPage: LiveData<Boolean>
        get() =  _isLastPage

    val isError = ObservableBoolean()

    private val query = "superman"
    private var totalPages = 0
    private var currentPage = 1

    init {
        getMovieList()
    }

    fun getMovieList() {
        if (currentPage < totalPages)
            currentPage++
        _isLastPage.postValue(currentPage == totalPages)
        fetchMovieList()
    }

    private fun fetchMovieList() {
        showLoading()

        viewModelScope.launch {
            val result = useCase.execute(BuildConfig.API_KEY, query, currentPage)
            when (result) {
                is Result.Success -> {
                    _movieVmList.postValue(
                        result.data.results.map { mapper.map(it) }
                    )
                    totalPages = result.data.totalPages
                }
                is Result.Error -> {
                    changeErrorState(true)
                }
                is Result.Exception -> {
                    changeErrorState(true)
                }
            }

            hideLoading()
        }
    }

    private fun showLoading() {
        _isLoading.postValue(true)
    }

    private fun hideLoading() {
        _isLoading.postValue(false)
    }

    private fun changeErrorState(state: Boolean) {
        isError.set(state)
    }

    private fun resetPages() {
        totalPages = 0
        currentPage = 1
    }

    fun onRetryClicked() {
        changeErrorState(false)
        resetPages()
        getMovieList()
    }

}