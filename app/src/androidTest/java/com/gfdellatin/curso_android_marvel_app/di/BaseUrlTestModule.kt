package com.gfdellatin.curso_android_marvel_app.di

import com.gfdellatin.curso_android_marvel_app.BuildConfig
import com.gfdellatin.curso_android_marvel_app.framework.di.qualifier.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BaseUrlTestModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "http://localhost:8080/"

}