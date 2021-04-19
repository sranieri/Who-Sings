package com.sample.whosings.usecase.question

import com.sample.whosings.data.model.Track
import com.sample.whosings.data.model.ui.Question
import com.sample.whosings.usecase.BaseUseCase
import kotlin.random.Random

/**
 * This usecase will create the current question
 */
class GetQuestionUseCase : BaseUseCase<GetQuestionUseCase.GetQuestionParams, Question>() {
    data class GetQuestionParams(
        val tracks: List<Track>,
        val index : Int
    )

    override suspend fun execute(params: GetQuestionParams): Question {
        val(tracks, index) = params
        val currentTrack = tracks[index]
        val trackId: Int = currentTrack.trackId ?: 0
        val rightAnswer = currentTrack.artistName ?: ""
        // to have other reasonable answers we take the atists from other songs
        val secondAnswer = tracks[(index + 10)%(tracks.size - 1)].artistName ?: ""
        val thirdAnswer = tracks[(index + 20)%(tracks.size - 1)].artistName ?: ""
        // get a random number to decide the right question position
        val random = Random.nextInt(1, 4)
        val first: String
        val second: String
        val third: String
        when(random){
            2 -> {
                second = rightAnswer
                first = secondAnswer
                third = thirdAnswer
            }
            3 -> {
                third = rightAnswer
                first = thirdAnswer
                second = secondAnswer
            }
            else -> {
                first = rightAnswer
                second = secondAnswer
                third = thirdAnswer
            }
        }
        return Question(trackId = trackId, rightAnswer = rightAnswer, firstAnswer = first, secondAnswer = second, thirdAnswer = third)
    }
}