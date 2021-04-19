package com.sample.whosings.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Header(
    @Json(name = "execute_time")
    var executeTime: Double?,
    @Json(name = "status_code")
    var statusCode: Int?
)