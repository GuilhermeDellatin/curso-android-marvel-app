package com.gfdellatin.core.data.repository

import com.gfdellatin.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    fun getAll(): Flow<List<Character>>

    suspend fun isFavorite(characterId: Int): Boolean

    suspend fun saveFavorite(character: Character)

    suspend fun deleteFavorite(character: Character)

}