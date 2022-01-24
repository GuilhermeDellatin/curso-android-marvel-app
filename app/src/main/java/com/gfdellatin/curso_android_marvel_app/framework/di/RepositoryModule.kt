package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.core.data.repository.CharactersRemoteDataSource
import com.gfdellatin.core.data.repository.CharactersRepository
import com.gfdellatin.curso_android_marvel_app.framework.RetrofitCharactersDataSource
import com.gfdellatin.curso_android_marvel_app.framework.network.response.DataWrapperResponse
import com.gfdellatin.curso_android_marvel_app.framework.remote.CharactersRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCharactersRepository(repository: CharactersRepositoryImpl): CharactersRepository

    @Binds
    fun bindRemoteDataSource(
        dataSource: RetrofitCharactersDataSource
    ): CharactersRemoteDataSource<DataWrapperResponse>

}