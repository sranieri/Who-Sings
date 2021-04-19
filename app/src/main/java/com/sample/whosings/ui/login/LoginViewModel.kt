package com.sample.whosings.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.whosings.usecase.user.LoginUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    val loginLiveData = MutableLiveData<Boolean>()

    fun loginUser(username: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                loginLiveData.postValue(loginUserUseCase.execute(username))
            }
        }
    }
}