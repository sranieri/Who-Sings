package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Message<T>(
    @Json(name = "body")
    var body: T?,
    @Json(name = "header")
    var header: Header?
)