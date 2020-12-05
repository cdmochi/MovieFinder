package chayapam.s.moviefinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chayapam.s.moviefinder.model.FavoriteAdapter
import chayapam.s.moviefinder.viewmodel.FavoriteViewModel
import chayapam.s.moviefinder.R
import chayapam.s.moviefinder.model.Movie
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteMovieListFragment : Fragment() {

    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : FavoriteAdapter
    private lateinit var container : LinearLayout
    private lateinit var model : FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorite_movie_list, container, false)

        return view
    }
    private fun showTabLayoutFromMainActivity() {
        requireActivity().ui_tabLayout.visibility = View.VISIBLE
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.container = view.findViewById(R.id.ui_noFavMoviesContainer) as LinearLayout
        this.recyclerView = view.findViewById(R.id.ui_favoriteRecycler) as RecyclerView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(requireActivity()).get(FavoriteViewModel::class.java)
        model.list.observe(viewLifecycleOwner,object : Observer<ArrayList<Movie>> {
            override fun onChanged(t: ArrayList<Movie>?) {
                if (t!!.isEmpty()) {
                    container.visibility = View.VISIBLE
                }
                adapter.setNewList(t)
                adapter.notifyDataSetChanged()
                container.visibility = View.INVISIBLE
            }

        })


        this.adapter = FavoriteAdapter(model.list.value!!)
        container.visibility = View.VISIBLE
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext
            ,LinearLayoutManager.VERTICAL,false)

        showTabLayoutFromMainActivity()



    }



}