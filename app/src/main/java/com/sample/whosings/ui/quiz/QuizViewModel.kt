package com.sample.whosings.ui.quiz

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.whosings.data.model.Track
import com.sample.whosings.data.model.ui.CheckAnswerResult
import com.sample.whosings.data.model.ui.Question
import com.sample.whosings.usecase.question.CheckQuestionParams
import com.sample.whosings.usecase.question.CheckQuestionUseCase
import com.sample.whosings.usecase.question.GetQuestionUseCase
import com.sample.whosings.usecase.tracks.GetLyricsUseCase
import com.sample.whosings.usecase.tracks.GetTracksUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class QuizViewModel(
    private val getTracksUseCase: GetTracksUseCase,
    private val getLyricsUseCase: GetLyricsUseCase,
    private val getQuestionUseCase: GetQuestionUseCase,
    private val checkQuestionUseCase: CheckQuestionUseCase
) : ViewModel() {

    val trackList = MutableLiveData<List<Track>>()
    val lyrics = MutableLiveData<String>()
    val currentQuestion = MutableLiveData<Triple<Question, Int, Int>>()
    val currentResult = MutableLiveData<CheckAnswerResult>()
    val quizResult = MutableLiveData<Int>()
    val countDown = MutableLiveData<Int>()

    var totalPoints = 0
    var currentQuestionIndex = 0
    val quizLenght = 10
    var stepCounter = 0

    val answerTimer: CountDownTimer = object : CountDownTimer(30000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            countDown.postValue((millisUntilFinished / 1000).toInt())
            //here you can have your logic to set text to edittext
        }

        override fun onFinish() {
            goToNextQuestion(0)
        }
    }

    fun fetchTracks() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                val tracks = getTracksUseCase.execute()
                tracks?.let {
                    trackList.postValue(tracks)
                }
            }
        }
    }

    fun getLyrics(trackId: String) {
        viewModelScope.launch {
            val text = getLyricsUseCase.execute(trackId)
            text?.let {
                lyrics.postValue(it)
            }
        }
    }

    fun startQuiz(){
        totalPoints = 0
        stepCounter = 0
        currentQuestionIndex = Random.nextInt(0, trackList.value?.size?.minus(10) ?: 1)
        getCurrentQuestion()
    }

    fun goToNextQuestion(delayTime: Long = 600){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                delay(delayTime)
                updateQuestionIndex()
                if(stepCounter < quizLenght){
                    getCurrentQuestion()
                } else {
                    // quiz finished
                    quizResult.postValue(totalPoints)
                }
            }
        }
    }

    fun getCurrentQuestion(){
        viewModelScope.launch {
            val tracks = trackList.value
            tracks?.let {
                val question = getQuestionUseCase.execute(
                    GetQuestionUseCase.GetQuestionParams(
                        it,
                        currentQuestionIndex
                    )
                )
                currentQuestion.value = Triple(question, stepCounter, quizLenght)
                startTimer()
            }
        }
    }

    fun checkAnswer(answer: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                resetTimer()
                val question = currentQuestion.value?.first
                val result = checkQuestionUseCase.execute(CheckQuestionParams(question, answer))
                currentResult.postValue(result)
                if(result.isSuccess){
                    totalPoints += 3
                }
            }
        }
    }

    private fun startTimer(){
        resetTimer()
        answerTimer.start()
    }

    private fun resetTimer(){
        answerTimer.cancel()
    }

    fun updateQuestionIndex(){
        val size = trackList.value?.size ?: 1
        currentQuestionIndex = (currentQuestionIndex + 1) % (size - 1)
        stepCounter++
    }
}