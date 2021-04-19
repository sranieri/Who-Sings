package com.sample.whosings.data.repository

import com.sample.whosings.data.api.TracksApiHelper
import com.sample.whosings.data.model.LyricsResponse
import com.sample.whosings.utils.Constants
import retrofit2.Response

class MusixMatchRepository(private val apiHelper: TracksApiHelper) {

    suspend fun getTracks() = apiHelper.getTracks(apikey = Constants.API_KEY, pageSize = 50)
    suspend fun getLyrics(trackId: String) : Response<LyricsResponse>? {
        return try {
            apiHelper.getLyrics(apikey = Constants.API_KEY, trackId = trackId)
        } catch (e: Exception) {
            null
        }
    }
}