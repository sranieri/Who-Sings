package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrimaryGenres(
    @Json(name = "music_genre_list")
    var musicGenreList: List<MusicGenreObject>?
)