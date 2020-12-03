package chayapam.s.moviefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import chayapam.s.moviefinder.model.Movie
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){


    companion object {
        const val TAG = "MainActivity"
        const val MOVIE_REQUEST_TAG = "MOVIES_REQUEST"
    }
    private lateinit var tabLayout : TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = ui_tabLayout

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val navController = this@MainActivity.findNavController(R.id.nav_host_fragment_container)
                when(tab?.position) {
                    0 -> { navController.navigate(R.id.mainActivityFragment) }
                    1 -> { navController.navigate(R.id.favoriteMovieListFragment) }
                }
            }





            override fun onTabUnselected(tab: TabLayout.Tab?) {
                
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })





    }



}