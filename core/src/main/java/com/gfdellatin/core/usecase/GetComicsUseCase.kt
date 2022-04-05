package com.gfdellatin.core.usecase

import com.gfdellatin.core.data.repository.CharactersRepository
import com.gfdellatin.core.domain.model.Comic
import com.gfdellatin.core.usecase.base.ResultStatus
import com.gfdellatin.core.usecase.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface GetComicsUseCase {

    operator fun invoke(params: GetComicsParams): Flow<ResultStatus<List<Comic>>>

    data class GetComicsParams(val characterId: Int)
}

class GetComicsUseCaseImpl @Inject constructor(
    private val repository: CharactersRepository
) : GetComicsUseCase, UseCase<GetComicsUseCase.GetComicsParams, List<Comic>>() {

    override suspend fun doWork(
        params: GetComicsUseCase.GetComicsParams
    ): ResultStatus<List<Comic>> {
        val comics = repository.getComics(params.characterId)
        return ResultStatus.Success(comics)
    }

}