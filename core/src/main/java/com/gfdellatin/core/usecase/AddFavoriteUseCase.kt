package com.gfdellatin.core.usecase

import com.gfdellatin.core.data.repository.FavoritesRepository
import com.gfdellatin.core.domain.model.Character
import com.gfdellatin.core.usecase.base.CoroutinesDispatchers
import com.gfdellatin.core.usecase.base.ResultStatus
import com.gfdellatin.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AddFavoriteUseCase {

    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>

    data class  Params(val characterId: Int, val name: String, val imageUrl: String)

}

class AddFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<AddFavoriteUseCase.Params, Unit>(), AddFavoriteUseCase {

    override suspend fun doWork(params: AddFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            favoritesRepository.saveFavorite(
                Character(params.characterId, params.name, params.imageUrl)
            )
            ResultStatus.Success(Unit)
        }
    }

}