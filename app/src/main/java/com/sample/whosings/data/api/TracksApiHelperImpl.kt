package com.sample.whosings.data.api

class TracksApiHelperImpl(private val service: TracksApiService) : TracksApiHelper {
    override suspend fun getTracks(
        apikey: String,
        chartName: String,
        page: Int,
        pageSize: Int,
        country: String,
        hasLirics: Int
    ) = service.getTracks(apikey, chartName, page, pageSize, country, hasLirics)

    override suspend fun getLyrics(apikey: String, trackId: String) = service.getLyrics(apikey, trackId)
}