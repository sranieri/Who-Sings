package com.sample.whosings.usecase.question

import com.sample.whosings.data.model.ui.CheckAnswerResult
import com.sample.whosings.data.model.ui.Question
import com.sample.whosings.usecase.BaseUseCase

/**
 * This usecase will check if the answer is correct
 */
class CheckQuestionUseCase : BaseUseCase<CheckQuestionParams, CheckAnswerResult>(){
    override suspend fun execute(params: CheckQuestionParams): CheckAnswerResult {
        val (question, answer) = params
        return question?.let {
            if (question.rightAnswer.equals(answer, true)) {
                CheckAnswerResult(true, answer)
            } else {
                CheckAnswerResult(false, question.rightAnswer, answer)
            }
        } ?: CheckAnswerResult(false)
    }

}

data class CheckQuestionParams(
    val question: Question?,
    val answer: String
)