package chayapam.s.moviefinder

import android.graphics.Movie
import com.google.gson.annotations.SerializedName


data class MovieList(
    @SerializedName("movies") val movies: List<chayapam.s.moviefinder.Movie>)
