package com.gfdellatin.curso_android_marvel_app.framework.imageloader

import android.widget.ImageView
import androidx.annotation.DrawableRes

interface ImageLoader {

    fun load(imageView: ImageView, imageUrl: String, @DrawableRes fallback: Int)

}