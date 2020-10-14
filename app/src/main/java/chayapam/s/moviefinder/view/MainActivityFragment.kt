package chayapam.s.moviefinder.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chayapam.s.moviefinder.*
import chayapam.s.moviefinder.model.Movie
import chayapam.s.moviefinder.model.MovieListAdapter
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class MainActivityFragment : Fragment() , MovieListAdapter.OnItemClickListener {

    companion object {
        const val TAG = "MainActivityFragment"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter : MovieListAdapter

    private var mMovies = mutableListOf<Movie>()

    //Volley Request Queue
    private lateinit var mQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mQueue = Volley.newRequestQueue(activity?.applicationContext)
        onInitializeRecycler()
        callApi()

    }


    private fun callApi() {
        Log.d(TAG,"Api is being called")
        val URL = "https://8fc4c056-61b0-4e8b-a053-463874366cce.mock.pstmn.io/get_movie_avaiable"
        val newMoviesList = ArrayList<Movie>()
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            URL,
            null,
            {
                val data = it
                try {
                    val movies = data.getJSONArray("movies")

                    for (i in 0..movies.length()) {
                        val jsonObj = movies.getJSONObject(i)
                        val name= jsonObj.getString("title_en")
                        val genre = jsonObj.getString("genre")
                        val sneakDate = jsonObj.getString("sneak_date")
                        val imgLink = jsonObj.getString("poster_url")
                        val detail = jsonObj.getString("synopsis_en")
                        val newMovie = Movie(name,genre,sneakDate,imgLink,detail)
                        newMoviesList.add(newMovie)
                        Log.d("MovieDetail","${newMovie.toString()}")
                    }

                } catch (j: JSONException) {
                    Log.d("jsonFailed", j.printStackTrace().toString())
                }
                mMovies.clear()
                mMovies.addAll(newMoviesList)
                adapter.setNewList(newMoviesList)
                adapter.notifyDataSetChanged()
            },
            {
                Log.d(TAG, "NetworkError: ${it.networkResponse}}")
            }
        )

        jsonObjectRequest.tag = MainActivity.MOVIE_REQUEST_TAG


        mQueue.add(jsonObjectRequest)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG,"onActivityCreated")
    }



    private fun onInitializeRecycler() {
        adapter = MovieListAdapter()
        adapter.setItemClickListener(this)
    }

    private fun setUpRecycler() {
        this.recyclerView.layoutManager = LinearLayoutManager(activity?.applicationContext,LinearLayoutManager.VERTICAL,false)
        this.recyclerView.adapter = adapter
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main_activity, container, false)
        initView(view)
        setUpRecycler()
        return view
    }

    private fun initView(view : View) {
        recyclerView = view.findViewById(R.id.ui_moviesRecyclerview) as RecyclerView
    }

    override fun onItemClick(position: Int) {
        val movieSelected = mMovies[position]

        val action = MainActivityFragmentDirections.actionMainActivityFragmentToCustomMovieDialog(movieSelected)
        Navigation.findNavController(requireView()).navigate(action)

        Toast.makeText(activity?.applicationContext,"FAV CLICKED",Toast.LENGTH_LONG).show()
    }


}