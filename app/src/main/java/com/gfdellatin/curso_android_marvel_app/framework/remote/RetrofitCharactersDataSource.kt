package com.gfdellatin.curso_android_marvel_app.framework.remote

import com.gfdellatin.core.data.repository.CharactersRemoteDataSource
import com.gfdellatin.core.domain.model.CharacterPaging
import com.gfdellatin.core.domain.model.Comic
import com.gfdellatin.core.domain.model.Event
import com.gfdellatin.curso_android_marvel_app.framework.network.MarvelApi
import com.gfdellatin.curso_android_marvel_app.framework.network.response.toCharacterModel
import com.gfdellatin.curso_android_marvel_app.framework.network.response.toComicModel
import com.gfdellatin.curso_android_marvel_app.framework.network.response.toEventModel
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

    override suspend fun fetchComics(characterId: Int): List<Comic> {
        return marvelApi.getComics(characterId).data.results.map {
            it.toComicModel()
        }
    }

    override suspend fun fetchEvents(characterId: Int): List<Event> {
        return marvelApi.getEvents(characterId).data.results.map {
            it.toEventModel()
        }
    }

}