package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.core.usecase.GetCharactersUseCase
import com.gfdellatin.core.usecase.GetCharactersUseCaseImpl
import com.gfdellatin.core.usecase.GetCharacterCategoriesUseCase
import com.gfdellatin.core.usecase.GetCharacterCategoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetComicsUseCase(
        useCase: GetCharacterCategoriesUseCaseImpl
    ): GetCharacterCategoriesUseCase
}