package com.hebreuyannis.cocktailapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hebreuyannis.cocktailapp.R
import com.hebreuyannis.cocktailapp.models.DrinkPresentation
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoriteRecyclerAdapter(private val itemClickListener: (DrinkPresentation) -> Unit) :
    ListAdapter<DrinkPresentation, FavoriteRecyclerAdapter.ViewHolder>(
        DiffCallback
    ) {

    companion object {
        @JvmStatic
        val DiffCallback = object : DiffUtil.ItemCallback<DrinkPresentation>() {
            override fun areItemsTheSame(
                oldItem: DrinkPresentation,
                newItem: DrinkPresentation
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: DrinkPresentation,
                newItem: DrinkPresentation
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun getItemWith(position: Int): DrinkPresentation = getItem(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(
            root,
            root.image_favorite,
            root.title_favorite
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    data class ViewHolder(val root: View, val image: ImageView, val title: TextView) :
        RecyclerView.ViewHolder(root) {

        fun bind(drink: DrinkPresentation, clickListener: (DrinkPresentation) -> Unit) {
            Glide.with(root.context)
                .load(drink.drinkThumb)
                .into(image)

            title.text = drink.name
            itemView.setOnClickListener { clickListener(drink) }
        }
    }
}

