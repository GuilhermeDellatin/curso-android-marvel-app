package com.gfdellatin.core.data.repository

import com.gfdellatin.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun getAll(): Flow<List<Character>>

    suspend fun saveFavorite(character: Character)

    suspend fun deleteFavorite(character: Character)

}