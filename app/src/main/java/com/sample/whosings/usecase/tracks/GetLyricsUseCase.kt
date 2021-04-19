package com.sample.whosings.usecase.tracks

import com.sample.whosings.data.repository.MusixMatchRepository
import com.sample.whosings.usecase.BaseUseCase

/**
 * This usecase will retrieve the lyrics of a given track
 */
class GetLyricsUseCase(private val repository: MusixMatchRepository) : BaseUseCase<String, String?>() {
    // by default we set the max lines to 3 to have at least three sentences from the song
    private val maxLines = 3

    override suspend fun execute(params: String): String? {
        val response = repository.getLyrics(params)

        if (response?.isSuccessful == true) {
            val s = response.body()?.message?.body?.lyrics?.lyricsBody ?: ""
            val lines = s.split("\n")
            var res = ""
            // Check if there are at least three lines, if not we just return the song as it is
            return if(lines.size > maxLines){
                // this boolean is used to check if one of the lines is blank,
                // in that case we take another line if present
                var addAnotherLine = false
                for(i in 0 until maxLines){
                    val line = lines[i]
                    if(!addAnotherLine && (line.isBlank() || line.isEmpty())){
                        addAnotherLine = true
                    }
                    res+=(line + "\n")
                }
                // if one of the lines is blank we take another line if present
                if(addAnotherLine && lines.size >= (maxLines + 1)){
                    res+=(lines[maxLines] + "\n")
                }
                res
            } else {
                // we don't have at least three lines so we return the song as it is
                s
            }
        }
        return null
    }
}