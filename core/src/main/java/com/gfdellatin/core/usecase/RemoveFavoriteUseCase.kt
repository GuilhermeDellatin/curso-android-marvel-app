package com.gfdellatin.core.usecase

import com.gfdellatin.core.data.repository.FavoritesRepository
import com.gfdellatin.core.domain.model.Character
import com.gfdellatin.core.usecase.base.CoroutinesDispatchers
import com.gfdellatin.core.usecase.base.ResultStatus
import com.gfdellatin.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoveFavoriteUseCase {

    operator fun invoke(params: Params) : Flow<ResultStatus<Unit>>

    data class  Params(val characterId: Int, val name: String, val imageUrl: String)

}

class RemoveFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
) : UseCase<RemoveFavoriteUseCase.Params, Unit>(), RemoveFavoriteUseCase {

    override suspend fun doWork(params: RemoveFavoriteUseCase.Params): ResultStatus<Unit> {
        return withContext(dispatchers.io()) {
            favoritesRepository.deleteFavorite(
                Character(params.characterId, params.name, params.imageUrl)
            )
            ResultStatus.Success(Unit)
        }
    }

}