package com.gfdellatin.curso_android_marvel_app.presentation.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gfdellatin.curso_android_marvel_app.databinding.ItemCharacterBinding
import com.gfdellatin.curso_android_marvel_app.framework.imageloader.ImageLoader
import com.gfdellatin.curso_android_marvel_app.presentation.common.GenericViewHolder

class FavoritesViewHolder(
    itemBinding: ItemCharacterBinding,
    private val imageLoader: ImageLoader
) : GenericViewHolder<FavoriteItem>(itemBinding) {

    private val textName: TextView = itemBinding.textName
    private val imageCharacter: ImageView = itemBinding.imageCharacter

    override fun bind(data: FavoriteItem) {
        textName.text = data.name
        imageLoader.load(imageCharacter, data.imageUrl)
    }

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader) : FavoritesViewHolder {
            val itemBinding = ItemCharacterBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)

            return FavoritesViewHolder(itemBinding, imageLoader)
        }
    }

}