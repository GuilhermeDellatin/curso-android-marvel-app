package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.core.usecase.base.AppCoroutinesDispatchers
import com.gfdellatin.core.usecase.base.CoroutinesDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutinesModule {

    @Provides
    fun bindDispatchers(dispatchers: AppCoroutinesDispatchers): CoroutinesDispatchers
}