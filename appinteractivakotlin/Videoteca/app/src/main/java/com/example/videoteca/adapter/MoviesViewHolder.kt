package com.example.videoteca.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.videoteca.model.Movie
import com.example.videoteca.utils.loadImage
import kotlinx.android.synthetic.main.item_movie.view.*

class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: Movie) {
        itemView.movieTitle.text = movie.name
        movie.cover?.let { mMovie ->
            itemView.movieCover.loadImage(mMovie)
        }
    }
}