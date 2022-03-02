package com.gfdellatin.curso_android_marvel_app.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataContainerResponse(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<CharacterResponse>
)
