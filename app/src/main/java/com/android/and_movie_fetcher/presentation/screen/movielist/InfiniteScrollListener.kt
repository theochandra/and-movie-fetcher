package com.android.and_movie_fetcher.presentation.screen.movielist

import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class InfiniteScrollListener(
    private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {

    private var isScrolling = false
    var isLoading = false
    var isLastPage = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager

        val sizeOfTheCurrentList = layoutManager.itemCount
        val visibleItems = layoutManager.childCount
        val topPosition = layoutManager.findFirstVisibleItemPosition()

        val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
        val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling

        if(shouldPaginate) {
            onLoadMore()
            isScrolling = false
        }
    }

}