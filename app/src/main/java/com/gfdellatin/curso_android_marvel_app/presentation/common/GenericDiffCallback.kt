package com.gfdellatin.curso_android_marvel_app.presentation.common

import androidx.recyclerview.widget.DiffUtil

class GenericDiffCallback<T : ListItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
       return oldItem.areItemsTheSame(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.areContentTheSame(newItem)
    }
}