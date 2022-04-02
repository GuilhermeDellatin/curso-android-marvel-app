package com.gfdellatin.core.data.repository

import com.gfdellatin.core.domain.model.CharacterPaging

interface CharactersRemoteDataSource {

    suspend fun fetchCharacters(queries: Map<String, String>): CharacterPaging

}