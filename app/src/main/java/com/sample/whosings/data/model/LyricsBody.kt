package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LyricsBody(
    @Json(name = "lyrics")
    var lyrics: Lyrics?
)