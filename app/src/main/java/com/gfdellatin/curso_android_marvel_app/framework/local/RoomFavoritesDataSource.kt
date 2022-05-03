package com.gfdellatin.curso_android_marvel_app.framework.local

import com.gfdellatin.core.data.repository.FavoritesLocalDataSource
import com.gfdellatin.core.domain.model.Character
import com.gfdellatin.curso_android_marvel_app.framework.db.dao.FavoriteDao
import com.gfdellatin.curso_android_marvel_app.framework.db.entity.FavoriteEntity
import com.gfdellatin.curso_android_marvel_app.framework.db.entity.toCharactersModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomFavoritesDataSource @Inject constructor(
    private val favoriteDao: FavoriteDao
) : FavoritesLocalDataSource {

    override fun getAll(): Flow<List<Character>> {
        return favoriteDao.loadFavorites().map {
            it.toCharactersModel()
        }
    }

    override suspend fun save(character: Character) {
        favoriteDao.insertFavorite(character.toFavoriteEntity())
    }

    override suspend fun delete(character: Character) {
        favoriteDao.deleteFavorite(character.toFavoriteEntity())
    }

    private fun Character.toFavoriteEntity() = FavoriteEntity(id, name, imageUrl)
}