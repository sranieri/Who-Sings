package com.sample.whosings.utils

import com.sample.whosings.data.model.Track

object Session {
    val tracks = mutableListOf<Track>()

    fun updateTracks(t: List<Track>){
        tracks.clear()
        tracks.addAll(t)
    }

    fun resetTracks(){
        tracks.clear()
    }
}