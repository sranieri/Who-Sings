package com.sample.whosings.ui.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.whosings.usecase.user.UpdateUserScoreUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizFinishedViewModel(val updateUserScoreUseCase: UpdateUserScoreUseCase) : ViewModel() {

    val userUpdated = MutableLiveData<Boolean>()

    fun updateUserScore(score: Int){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                userUpdated.postValue(updateUserScoreUseCase.execute(score))
            }
        }
    }
}