package com.gfdellatin.curso_android_marvel_app.framework.network.response

data class DataContainerResponse(
    val offset: Int,
    val total: Int,
    val results: List<CharacterResponse>
)
