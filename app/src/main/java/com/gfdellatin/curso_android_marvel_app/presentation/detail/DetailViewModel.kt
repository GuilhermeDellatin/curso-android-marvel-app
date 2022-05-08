package com.gfdellatin.curso_android_marvel_app.presentation.detail

import androidx.lifecycle.ViewModel
import com.gfdellatin.core.usecase.AddFavoriteUseCase
import com.gfdellatin.core.usecase.CheckFavoriteUseCase
import com.gfdellatin.core.usecase.GetCharacterCategoriesUseCase
import com.gfdellatin.core.usecase.base.CoroutinesDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    getCharacterCategoriesUseCase: GetCharacterCategoriesUseCase,
    checkFavoriteUseCase: CheckFavoriteUseCase,
    addFavoriteUseCase: AddFavoriteUseCase,
    coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    val categories = UiActionStateLiveData(
        coroutinesDispatchers.main(),
        getCharacterCategoriesUseCase
    )

    val favorite = FavoriteUiActionStateLiveData(
        coroutinesDispatchers.main(),
        checkFavoriteUseCase,
        addFavoriteUseCase
    )

}