package chayapam.s.moviefinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_view.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    val list : ArrayList<Movie> = ArrayList()

    fun setNewList(newList : List<Movie>) {
        list.clear()
        list.addAll(newList)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.ui_movieImage
        val movieName = itemView.ui_movieTitle
        val date = itemView.ui_date
        val cat = itemView.ui_movieCat

        fun bind(position : Int) {
            val itemAtPos = list[position]
            loadImageWithPicasso(itemAtPos.posterImage,image)
            movieName.text = itemAtPos.name
            date.text = itemAtPos.date
            cat.text = itemAtPos.cat

        }

    }

    fun loadImageWithPicasso(uri : String, imageView : ImageView) {
        Picasso.get().load(uri).into(imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}