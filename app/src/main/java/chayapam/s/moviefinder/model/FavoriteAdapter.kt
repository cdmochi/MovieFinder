package chayapam.s.moviefinder.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import chayapam.s.moviefinder.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_view.view.*

class FavoriteAdapter(var list : ArrayList<Movie>) : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){



    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.ui_movieImage
        val movieName = itemView.ui_movieTitle
        val date = itemView.ui_date
        val genere = itemView.ui_movieCat

        fun bind(position : Int) {
            val itemAtPos = list[position]
            loadImageWithPicasso(itemAtPos.posterImage,image)
            movieName.text = itemAtPos.name
            date.text = itemAtPos.date
            genere.text = itemAtPos.genre

        }

    }

    fun setNewList(newList : ArrayList<Movie>) {
        this.list = newList
    }

    fun loadImageWithPicasso(uri : String, imageView : ImageView) {
        Picasso.get().load(uri).fit()
            .into(imageView)
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