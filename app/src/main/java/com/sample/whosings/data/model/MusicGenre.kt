package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MusicGenre(
    @Json(name = "music_genre_id")
    var musicGenreId: Int?,
    @Json(name = "music_genre_name")
    var musicGenreName: String?,
    @Json(name = "music_genre_name_extended")
    var musicGenreNameExtended: String?,
    @Json(name = "music_genre_parent_id")
    var musicGenreParentId: Int?,
    @Json(name = "music_genre_vanity")
    var musicGenreVanity: String?
)