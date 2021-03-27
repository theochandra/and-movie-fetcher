package com.android.and_movie_fetcher.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.squareup.picasso.Picasso

@BindingConversion
fun setVisibility(state: Boolean): Int {
    return if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, posterPath: String?) {
    posterPath?.let {
        val url = "https://image.tmdb.org/t/p/w92$it"
        Picasso.get()
            .load(url)
            .centerCrop()
            .fit()
            .into(imageView)
    }
}