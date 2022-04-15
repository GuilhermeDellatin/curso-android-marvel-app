package com.gfdellatin.core.data.repository

import com.gfdellatin.core.domain.model.CharacterPaging
import com.gfdellatin.core.domain.model.Comic
import com.gfdellatin.core.domain.model.Event

interface CharactersRemoteDataSource {

    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging

    suspend fun fetchComics(characterId: Int): List<Comic>

    suspend fun fetchEvents(characterId: Int): List<Event>

}