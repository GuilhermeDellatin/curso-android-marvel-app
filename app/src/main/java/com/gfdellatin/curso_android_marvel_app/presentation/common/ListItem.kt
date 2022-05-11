package com.gfdellatin.curso_android_marvel_app.presentation.common

interface ListItem {

    val key: Long

    fun areItemsTheSame(other: ListItem) = this.key == other.key

    fun areContentTheSame(other: ListItem) = this == other

}