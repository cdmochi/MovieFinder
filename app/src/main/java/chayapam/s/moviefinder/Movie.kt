package chayapam.s.moviefinder

import com.google.gson.annotations.SerializedName
import retrofit2.http.Path

data class Movie(


    @SerializedName("title_en")val name : String,
    @SerializedName("genre") val cat : String,
    @SerializedName("release_date")val date : String,
    @SerializedName("poster_url")val posterImage : String )



