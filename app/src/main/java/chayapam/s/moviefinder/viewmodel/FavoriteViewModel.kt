package chayapam.s.moviefinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import chayapam.s.moviefinder.model.Movie

class FavoriteViewModel : ViewModel() {

    val list = MutableLiveData<ArrayList<Movie>>()

    init {
        list.value = ArrayList()
    }

    fun addMovie(movie : Movie) {
        list.value?.add(movie)
    }


}