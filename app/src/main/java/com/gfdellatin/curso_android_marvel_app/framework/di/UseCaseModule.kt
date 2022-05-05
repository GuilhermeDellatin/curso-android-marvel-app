package com.gfdellatin.curso_android_marvel_app.framework.di

import com.gfdellatin.core.usecase.AddFavoriteUseCase
import com.gfdellatin.core.usecase.AddFavoriteUseCaseImpl
import com.gfdellatin.core.usecase.GetCharacterCategoriesUseCase
import com.gfdellatin.core.usecase.GetCharacterCategoriesUseCaseImpl
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
    fun bindGetCharactersUseCase(useCase: GetCharactersUseCaseImpl): GetCharactersUseCase

    @Binds
    fun bindGetComicsUseCase(
        useCase: GetCharacterCategoriesUseCaseImpl
    ): GetCharacterCategoriesUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoriteUseCaseImpl): AddFavoriteUseCase
}