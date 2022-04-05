package com.gfdellatin.curso_android_marvel_app.framework.remote

import androidx.paging.PagingSource
import com.gfdellatin.core.data.repository.CharactersRemoteDataSource
import com.gfdellatin.core.data.repository.CharactersRepository
import com.gfdellatin.core.domain.model.Character
import com.gfdellatin.core.domain.model.Comic
import com.gfdellatin.curso_android_marvel_app.framework.network.response.DataWrapperResponse
import com.gfdellatin.curso_android_marvel_app.framework.paging.CharactersPagingSource
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharactersRemoteDataSource
): CharactersRepository {

    override fun getCharacters(query: String): PagingSource<Int, Character> {
        return CharactersPagingSource(remoteDataSource, query)
    }

    override suspend fun getComics(characterId: Int): List<Comic> {
        return remoteDataSource.fetchComics(characterId)
    }

}