package com.sample.whosings.data.api

import com.sample.whosings.data.model.LyricsResponse
import com.sample.whosings.data.model.TracksResponse
import retrofit2.Response

interface TracksApiHelper {

    suspend fun getTracks(apikey: String,chartName: String = "top",page: Int = 1,pageSize: Int = 100,country: String = "uk",hasLirics: Int = 1) : Response<TracksResponse>

    suspend fun getLyrics(apikey: String,trackId: String) : Response<LyricsResponse>
}