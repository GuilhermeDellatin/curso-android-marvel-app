package com.gfdellatin.core.usecase

import com.gfdellatin.core.data.repository.FavoritesRepository
import com.gfdellatin.core.usecase.base.CoroutinesDispatchers
import com.gfdellatin.core.usecase.base.ResultStatus
import com.gfdellatin.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CheckFavoriteUseCase {

    operator fun invoke(params: Params) : Flow<ResultStatus<Boolean>>

    data class  Params(val characterId: Int)

}

class CheckFavoriteUseCaseImpl @Inject constructor(
    private val favoritesRepository: FavoritesRepository,
    private val dispatchers: CoroutinesDispatchers
): UseCase<CheckFavoriteUseCase.Params, Boolean>(), CheckFavoriteUseCase {

    override suspend fun doWork(params: CheckFavoriteUseCase.Params): ResultStatus<Boolean> {
        return withContext(dispatchers.io()) {
            val isFavorite = favoritesRepository.isFavorite(params.characterId)
            ResultStatus.Success(isFavorite)
        }
    }
}