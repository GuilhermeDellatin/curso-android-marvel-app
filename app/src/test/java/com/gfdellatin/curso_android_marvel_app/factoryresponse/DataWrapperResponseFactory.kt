package com.gfdellatin.curso_android_marvel_app.factoryresponse

import com.gfdellatin.curso_android_marvel_app.framework.network.response.CharacterResponse
import com.gfdellatin.curso_android_marvel_app.framework.network.response.DataContainerResponse
import com.gfdellatin.curso_android_marvel_app.framework.network.response.DataWrapperResponse
import com.gfdellatin.curso_android_marvel_app.framework.network.response.ThumbnailResponse

class DataWrapperResponseFactory {

    fun create() = DataWrapperResponse(
        copyright = "",
        data = DataContainerResponse(
            offset = 0,
            total = 2,
            results = listOf(
                CharacterResponse(
                    id = "1011334",
                    name = "3-D man",
                    thumbnail = ThumbnailResponse(
                        path = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784",
                        extension = "jpg"
                    )
                ),
                CharacterResponse(
                    id = "1017100",
                    name = "A-Bomb (HAS)",
                    thumbnail = ThumbnailResponse(
                        path = "https://i.annihil.us/u/prod/marvel/i/mg/3/20/5232158de5b16",
                        extension = "jpg"
                    )
                )
            )
        )
    )

}