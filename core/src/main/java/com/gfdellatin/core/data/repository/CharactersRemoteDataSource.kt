package com.gfdellatin.core.data.repository

import com.gfdellatin.core.domain.model.CharacterPaging
import com.gfdellatin.core.domain.model.Comic

interface CharactersRemoteDataSource {

    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging

    suspend fun fetchComics(characterId: Int): List<Comic>

}