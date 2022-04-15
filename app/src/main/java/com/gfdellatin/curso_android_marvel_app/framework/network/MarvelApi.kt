package com.gfdellatin.curso_android_marvel_app.framework.network

import com.gfdellatin.curso_android_marvel_app.framework.network.response.CharacterResponse
import com.gfdellatin.curso_android_marvel_app.framework.network.response.ComicResponse
import com.gfdellatin.curso_android_marvel_app.framework.network.response.DataWrapperResponse
import com.gfdellatin.curso_android_marvel_app.framework.network.response.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MarvelApi {

    @GET("characters")
    suspend fun getCharacters(
        @QueryMap
        queries: Map<String, String>
    ): DataWrapperResponse<CharacterResponse>

    @GET("characters/{characterId}/comics")
    suspend fun getComics(
        @Path("characterId")
        characterId: Int
    ): DataWrapperResponse<ComicResponse>

    @GET("characters/{characterId}/events")
    suspend fun getEvents(
        @Path("characterId")
        characterId: Int
    ): DataWrapperResponse<EventResponse>

}