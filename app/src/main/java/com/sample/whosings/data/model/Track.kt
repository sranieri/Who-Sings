package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Track(
    @Json(name = "album_id")
    var albumId: Int?,
    @Json(name = "album_name")
    var albumName: String?,
    @Json(name = "artist_id")
    var artistId: Int?,
    @Json(name = "artist_name")
    var artistName: String?,
    @Json(name = "commontrack_id")
    var commontrackId: Int?,
    @Json(name = "explicit")
    var explicit: Int?,
    @Json(name = "has_lyrics")
    var hasLyrics: Int?,
    @Json(name = "has_richsync")
    var hasRichsync: Int?,
    @Json(name = "has_subtitles")
    var hasSubtitles: Int?,
    @Json(name = "instrumental")
    var instrumental: Int?,
    @Json(name = "num_favourite")
    var numFavourite: Int?,
    @Json(name = "primary_genres")
    var primaryGenres: PrimaryGenres?,
    @Json(name = "restricted")
    var restricted: Int?,
    @Json(name = "track_edit_url")
    var trackEditUrl: String?,
    @Json(name = "track_id")
    var trackId: Int?,
    @Json(name = "track_name")
    var trackName: String?,
    @Json(name = "track_name_translation_list")
    var trackNameTranslationList: List<Any>?,
    @Json(name = "track_rating")
    var trackRating: Int?,
    @Json(name = "track_share_url")
    var trackShareUrl: String?,
    @Json(name = "updated_time")
    var updatedTime: String?
)