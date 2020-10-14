package chayapam.s.moviefinder.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import chayapam.s.moviefinder.R
import com.google.android.play.core.splitinstall.c
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item_view.view.*
import java.util.*
import kotlin.collections.ArrayList

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {


    var onItemClickListener : OnItemClickListener? = null
    var list : ArrayList<Movie> = ArrayList()

    fun setNewList(newList : ArrayList<Movie>) {
        list = newList
    }




    fun setItemClickListener(onClickInterface : OnItemClickListener) {
        this.onItemClickListener = onClickInterface
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.ui_movieImage
        val movieName = itemView.ui_movieTitle
        val date = itemView.ui_date
        val genere = itemView.ui_movieCat

        fun bind(position : Int) {
            val itemAtPos = list[position]
            loadImageWithPicasso(itemAtPos.posterImage,image)
            movieName.text = itemAtPos.name
            genere.text = itemAtPos.genre

            val dateInt = itemAtPos.date.substring(5,7).toInt()
            val c = Calendar.getInstance().get(dateInt)

            date.text = formatDate(c)


        }

    }

    fun formatDate(c : Int) : String{
        val formatter = Formatter()
        val monthName = formatter.format("%tb",c)
        return monthName.toString()
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
        if (onItemClickListener != null) {
            holder.itemView.ui_cardviewContainer.setOnClickListener {
                onItemClickListener!!.onItemClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    interface OnItemClickListener {
        fun onItemClick(position : Int)
    }
}