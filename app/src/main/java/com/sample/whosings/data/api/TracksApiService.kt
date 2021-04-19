package com.sample.whosings.data.api

import com.sample.whosings.data.model.LyricsResponse
import com.sample.whosings.data.model.TracksResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TracksApiService {

    @GET("chart.tracks.get")
    suspend fun getTracks(
        @Query("apikey")apikey: String,
        @Query("chart_name") chartName: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int,
        @Query("country") country: String,
        @Query("f_has_lyrics") hasLirics: Int) : Response<TracksResponse>

    @GET("track.lyrics.get")
    suspend fun getLyrics(
        @Query("apikey")apikey: String,
        @Query("track_id")trackId: String
    ) : Response<LyricsResponse>
}