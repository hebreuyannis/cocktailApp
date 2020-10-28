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
import com.hebreuyannis.cocktailapp.models.IngredientPresentation
import kotlinx.android.synthetic.main.item_ingredient.view.*

class IngredientRecyclerAdapter(private val itemClickListener: (IngredientPresentation) -> Unit) :
    ListAdapter<IngredientPresentation, IngredientRecyclerAdapter.ViewHolder>(
        DiffCallback
    ) {

    companion object {
        @JvmStatic
        val DiffCallback = object : DiffUtil.ItemCallback<IngredientPresentation>() {
            override fun areItemsTheSame(
                oldItem: IngredientPresentation,
                newItem: IngredientPresentation
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: IngredientPresentation,
                newItem: IngredientPresentation
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val root = inflater.inflate(R.layout.item_ingredient, parent, false)
        return ViewHolder(
            root,
            root.image_ingrediant,
            root.title_ingredient
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    data class ViewHolder(val root: View, val image: ImageView, val title: TextView) :
        RecyclerView.ViewHolder(root) {

        fun bind(
            ingredient: IngredientPresentation,
            clickListener: (IngredientPresentation) -> Unit
        ) {
            Glide.with(root.context)
                .load(ingredient.ingredientThumb)
                .into(image)

            title.text = ingredient.name
            itemView.setOnClickListener { clickListener(ingredient) }
        }
    }
}

