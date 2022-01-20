package com.gfdellatin.curso_android_marvel_app.framework.remote

import com.gfdellatin.core.data.repository.CharactersRemoteDataSource
import com.gfdellatin.curso_android_marvel_app.framework.network.MarvelApi
import com.gfdellatin.curso_android_marvel_app.framework.network.response.DataWrapperResponse
import javax.inject.Inject

class RetrofitCharactersDataSource @Inject constructor(
    private val marvelApi: MarvelApi
) : CharactersRemoteDataSource<DataWrapperResponse> {

    override suspend fun fetchCharacters(queries: Map<String, String>): DataWrapperResponse {
        return marvelApi.getCharacters(queries)
    }

}