package com.sample.whosings.usecase.tracks

import com.sample.whosings.data.model.Track
import com.sample.whosings.data.repository.MusixMatchRepository
import com.sample.whosings.usecase.BaseUseCaseNoInput
import com.sample.whosings.utils.Session

/**
 * This usecase will retrieve the tracklist,
 * since there is an API limitation to the number of daily usages we have a cache to keep the tracks until the app is closed.
 */
class GetTracksUseCase(private val repository: MusixMatchRepository) : BaseUseCaseNoInput<List<Track>?>() {
    override suspend fun execute(): List<Track>? {
        val tracks = Session.tracks
        // if there are no tracks into the cache we retrieve them from the API
        if(tracks.isEmpty()) {
            var apiTracks : List<Track>? = null
            try {
                val response = repository.getTracks()
                if (response.isSuccessful) {
                    apiTracks = response.body()?.message?.body?.trackList?.map { track ->
                        track.track
                    }?.filterNotNull()
                }
            } catch (e: Exception) {

            }
            // after retrieving the tracks from the API we update the cache
            apiTracks?.let {
                Session.updateTracks(it)
            }
            return apiTracks
        } else {
            return tracks
        }
    }
}