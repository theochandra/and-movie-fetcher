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

    val isLoading = ObservableBoolean()

    init {
        getMovieList()
    }

    fun getMovieList() {
        isLoading.set(true)
        viewModelScope.launch {
            val result = useCase.execute(BuildConfig.API_KEY, 1)
            when (result) {
                is Result.Success -> {
                    _movieVmList.postValue(
                        result.data.results.map { mapper.map(it) }
                    )
                }
                is Result.Error -> {

                }
                is Result.Exception -> {

                }
            }
            isLoading.set(false)
        }
    }

}