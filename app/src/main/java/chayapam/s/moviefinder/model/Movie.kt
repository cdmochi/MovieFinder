package chayapam.s.moviefinder.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val name : String,
    val genre : String,
    val date : String,
    val posterImage : String,
    var detail : String
) : Parcelable



