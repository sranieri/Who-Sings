package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Lyrics(
    @Json(name = "explicit")
    var explicit: Int?,
    @Json(name = "lyrics_body")
    var lyricsBody: String?,
    @Json(name = "lyrics_copyright")
    var lyricsCopyright: String?,
    @Json(name = "lyrics_id")
    var lyricsId: Int?,
    @Json(name = "pixel_tracking_url")
    var pixelTrackingUrl: String?,
    @Json(name = "script_tracking_url")
    var scriptTrackingUrl: String?,
    @Json(name = "updated_time")
    var updatedTime: String?
)