package chayapam.s.moviefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import chayapam.s.moviefinder.model.Movie

class MainActivity : AppCompatActivity(){


    companion object {
        const val TAG = "MainActivity"
        const val MOVIE_REQUEST_TAG = "MOVIES_REQUEST"
    }
    val favoriteList = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }



}