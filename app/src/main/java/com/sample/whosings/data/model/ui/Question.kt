package com.sample.whosings.data.model.ui

data class Question(
    val question: String? = null,
    val trackId: Int,
    val firstAnswer: String,
    val secondAnswer: String,
    val thirdAnswer: String,
    val rightAnswer: String
)