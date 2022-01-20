package com.gfdellatin.core.data.repository

import androidx.paging.PagingSource
import com.gfdellatin.core.domain.model.Character

interface CharactersRepository {

    fun getCharacters(query: String): PagingSource<Int, Character>
}