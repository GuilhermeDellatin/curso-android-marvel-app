package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.curso_android_marvel_app.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlModule {

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

}