package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TracksBody(
    @Json(name = "track_list")
    var trackList: List<TrackObject>?
)