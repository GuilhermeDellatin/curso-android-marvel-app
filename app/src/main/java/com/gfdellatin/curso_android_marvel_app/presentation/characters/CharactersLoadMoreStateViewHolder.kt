package com.gfdellatin.curso_android_marvel_app.presentation.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.gfdellatin.curso_android_marvel_app.databinding.ItemCharacterLoadingMoreStateBinding

class CharactersLoadMoreStateViewHolder(
   itemBinding: ItemCharacterLoadingMoreStateBinding,
   retry: () -> Unit
): RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = ItemCharacterLoadingMoreStateBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoadingMore
    private val textTryAgainMessage = binding.textTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBarLoadingMore.isVisible = loadState is LoadState.Loading
        textTryAgainMessage.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharactersLoadMoreStateViewHolder {
            val itemBinding = ItemCharacterLoadingMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return CharactersLoadMoreStateViewHolder(itemBinding, retry)
        }
    }
}