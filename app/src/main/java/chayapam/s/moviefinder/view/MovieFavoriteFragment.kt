package chayapam.s.moviefinder.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import chayapam.s.moviefinder.model.Movie
import chayapam.s.moviefinder.R
import chayapam.s.moviefinder.viewmodel.FavoriteViewModel
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_favorite_fragment.*

class MovieFavoriteFragment : Fragment(){

    private lateinit var header : TextView
    private lateinit var genre : TextView
    private lateinit var posterImage: ImageView
    private lateinit var text : TextView
    private lateinit var addFavMovie : MaterialButton
    private lateinit var model : FavoriteViewModel

    private lateinit var movie : Movie


    private val args : MovieFavoriteFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.movie_favorite_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        movie = args.movie
        bindingViews()

        ui_favorite_button.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
            model.addMovie(movie)
        }

        ui_cancel_button.setOnClickListener{
            Navigation.findNavController(it).navigateUp()
        }


    }

    private fun bindingViews() {
        header.text = movie.name
        genre.text = movie.genre
        loadImageFromUrl(movie.posterImage,posterImage)
        text.text = movie.detail
    }

    private fun loadImageFromUrl(uri : String, view : ImageView) {
        Picasso.get()
            .load(uri).fit()
            .into(view)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        model = ViewModelProviders.of(requireActivity()).get(FavoriteViewModel::class.java)
        hideTabLayoutFromMainActivity()
    }

    private fun hideTabLayoutFromMainActivity() {
        requireActivity().ui_tabLayout.visibility = View.GONE
    }


    private fun initView(view : View) {

        text = view.findViewById(R.id.ui_text_content) as TextView
        posterImage = view.findViewById(R.id.ui_movie_image_Fav) as ImageView
        header = view.findViewById(R.id.ui_header) as TextView
        genre = view.findViewById(R.id.ui_genre) as TextView
        addFavMovie = ui_favorite_button as MaterialButton

    }




}