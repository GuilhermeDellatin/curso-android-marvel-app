package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.core.data.repository.FavoritesLocalDataSource
import com.gfdellatin.core.data.repository.FavoritesRepository
import com.gfdellatin.curso_android_marvel_app.framework.FavoritesRepositoryImpl
import com.gfdellatin.curso_android_marvel_app.framework.local.RoomFavoritesDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface FavoritesRepositoryModule {

    @Binds
    fun bindFavoritesRepository(repository: FavoritesRepositoryImpl): FavoritesRepository

    @Binds
    fun bindLocalDataSource(
        dataSource: RoomFavoritesDataSource
    ): FavoritesLocalDataSource

}