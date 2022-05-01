package com.gfdellatin.core.data.repository

import com.gfdellatin.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface FavoritesLocalDataSource {

    suspend fun getAll(): Flow<List<Character>>

    suspend fun save(character: Character)

    suspend fun delete(character: Character)

}