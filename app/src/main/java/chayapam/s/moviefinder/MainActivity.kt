package chayapam.s.moviefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MovieListAdapter
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var tabLayout : TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        val gsonBuilder = GsonBuilder()
            .registerTypeAdapter(MovieList::class.java,Deserilizer<MovieList>())
            .create()

        setUpRecycler()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.majorcineplex.com/apis/")
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
            .build()

        val MoviesApi = retrofit.create(MoviesApi::class.java)

        val call = MoviesApi.getMovies()


            call.enqueue(object : Callback<MovieList>{
                override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                    Log.d("MainActivity","success to obtain movie")
                    val movieListObj = response.body()!!
                    adapter.setNewList(movieListObj.movies)
                }

                override fun onFailure(call: Call<MovieList>, t: Throwable) {
                    Log.d("MainActivity","Fail to obtain movie:${t.printStackTrace()}")
                }

            })









    }

    private fun initViews() {
        this.recyclerView = ui_allMoviesRecycler
        this.tabLayout = ui_tabLayout
    }

    private fun setUpRecycler() {
        adapter = MovieListAdapter()
        linearLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        this.recyclerView.layoutManager = linearLayoutManager
        this.recyclerView.adapter = adapter
    }
}