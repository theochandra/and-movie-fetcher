package com.android.and_movie_fetcher.databinding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.android.and_movie_fetcher.R
import com.android.and_movie_fetcher.utils.formatDate
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

@BindingAdapter("averageVote")
fun setRating(textView: TextView, averageVote: Double) {
    textView.text = averageVote.toString()
}

@BindingAdapter("formatDate")
fun setFormattedDate(textView: TextView, strDate: String?) {
    var date = textView.context.getString(R.string.lbl_release_date_unavailable)
    if (!strDate.isNullOrEmpty()) date = formatDate(strDate)
    textView.text = date
}

@BindingAdapter("overview")
fun setOverview(textView: TextView, overview: String) {
    var temp = textView.context.getString(R.string.lbl_plot_unknown)
    if (!overview.isNullOrEmpty()) temp = overview
    textView.text = temp
}