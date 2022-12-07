package com.openwebinars.filmapp.view

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.openwebinars.filmapp.R
import com.openwebinars.filmapp.model.Film
import com.openwebinars.filmapp.databinding.ItemFilmBinding

class FilmsAdapter(
    private val films: List<Film>,
    private val onClickFilm: (Film) -> Unit
) : RecyclerView.Adapter<FilmsAdapter.FilmsViewHolder>() {

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val item = films[position]
        holder.bind(item, onClickFilm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FilmsViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))
    }

    override fun getItemCount() = films.size

    class FilmsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemFilmBinding.bind(view)

        fun bind(item: Film, onClickFilm: (Film) -> Unit) {
            binding.tvTitle.text = item.name
            binding.tvDirector.text = item.director
            binding.tvRate.text = item.rate
            binding.ivCover.setImageDrawable(getImageSrc(item.image, itemView.context))
            binding.cardFilm.setOnClickListener { onClickFilm(item) }
        }

        private fun getImageSrc(name: String, context: Context): Drawable {
            val resources: Resources = context.resources
            val resourceId: Int = resources.getIdentifier(
                name, "drawable",
                context.packageName
            )
            return resources.getDrawable(resourceId, null)
        }
    }
}