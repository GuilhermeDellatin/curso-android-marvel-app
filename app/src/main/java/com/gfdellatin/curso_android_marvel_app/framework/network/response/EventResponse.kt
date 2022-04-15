package com.gfdellatin.curso_android_marvel_app.framework.network.response

import com.gfdellatin.core.domain.model.Event
import com.google.gson.annotations.SerializedName

data class EventResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("thumbnail")
    val thumbnail: ThumbnailResponse
)

fun EventResponse.toEventModel() : Event {
    return Event(
        id = this.id,
        imageUrl = this.thumbnail.getHttpsUrl()
    )
}
