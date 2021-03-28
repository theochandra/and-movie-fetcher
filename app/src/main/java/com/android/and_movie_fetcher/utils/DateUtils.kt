package com.android.and_movie_fetcher.utils

import java.text.SimpleDateFormat

/**
 * a method to format string date
 * input -> string date with format yyyy-MM-dd
 * return -> string date with format dd MM yyyy
 */
fun formatDate(strDate: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd")
    val formatter = SimpleDateFormat("dd MMM yyyy")
    return formatter.format(parser.parse(strDate))
}