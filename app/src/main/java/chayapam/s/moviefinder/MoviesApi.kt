package chayapam.s.moviefinder

import retrofit2.Call
import retrofit2.http.GET

interface MoviesApi {


    @GET("get_movie_avaiable")
    fun getMovies() : Call<MovieList>


}