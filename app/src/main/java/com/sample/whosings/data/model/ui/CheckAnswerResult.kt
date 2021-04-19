package com.sample.whosings.data.model.ui

data class CheckAnswerResult(
    val isSuccess: Boolean = false,
    val answerText: String? = null,
    val wrongAnswerText: String? = null
)
