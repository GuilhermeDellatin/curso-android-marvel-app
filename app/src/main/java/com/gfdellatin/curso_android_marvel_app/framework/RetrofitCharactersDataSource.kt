package com.gfdellatin.curso_android_marvel_app.framework

import com.gfdellatin.core.data.repository.CharactersRemoteDataSource
import com.gfdellatin.core.domain.model.CharacterPaging
import com.gfdellatin.curso_android_marvel_app.framework.network.MarvelApi
import com.gfdellatin.curso_android_marvel_app.framework.network.response.toCharacterModel
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource {

    override suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging {
        val data = marvelApi.getCharacters(queries).data
        val characters = data.results.map {
            it.toCharacterModel()
        }

        return CharacterPaging(
            data.offset,
            data.total,
            characters
        )
    }

}