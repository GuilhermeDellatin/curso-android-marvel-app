package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.core.usecase.GetCharactersUseCase
import com.gfdellatin.core.usecase.GetCharactersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase
}