package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.curso_android_marvel_app.framework.imageloader.GlideImageLoader
import com.gfdellatin.curso_android_marvel_app.framework.imageloader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface AppModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader) : ImageLoader
}