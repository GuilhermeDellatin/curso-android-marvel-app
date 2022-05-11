package com.gfdellatin.curso_android_marvel_app.presentation.favorites

import com.gfdellatin.curso_android_marvel_app.presentation.common.ListItem

data class FavoriteItem(
    val id: Int,
    val name: String,
    val imageUrl: String,
    override val key: Long = id.toLong()
) : ListItem
