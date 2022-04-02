package com.gfdellatin.curso_android_marvel_app.presentation.characters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.gfdellatin.core.domain.model.Character
import com.gfdellatin.curso_android_marvel_app.framework.imageloader.ImageLoader
import com.gfdellatin.curso_android_marvel_app.util.OnCharacterItemClick
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
    private val imageLoader: ImageLoader,
    private val onItemClick: OnCharacterItemClick
) : PagingDataAdapter<Character, CharactersViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder.create(parent, imageLoader, onItemClick)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }

    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(
                oldItem: Character,
                newItem: Character
            ): Boolean {
                return oldItem == newItem
            }

        }

    }
}