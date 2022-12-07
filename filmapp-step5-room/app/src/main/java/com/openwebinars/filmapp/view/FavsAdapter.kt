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

class FavsAdapter(
    private val films: List<Film>
) : RecyclerView.Adapter<FavsAdapter.FavsViewHolder>() {

    override fun onBindViewHolder(holder: FavsViewHolder, position: Int) {
        val item = films[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavsViewHolder(layoutInflater.inflate(R.layout.item_film, parent, false))
    }

    override fun getItemCount() = films.size

    class FavsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemFilmBinding.bind(view)

        fun bind(item: Film) {
            binding.tvTitle.text = item.name
            binding.tvDirector.text = item.director
            binding.tvRate.text = item.rate
            binding.ivCover.setImageDrawable(getImageSrc(item.image, itemView.context))
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