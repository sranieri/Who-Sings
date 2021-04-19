package com.sample.whosings.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.whosings.data.model.Track
import com.sample.whosings.db.model.User
import com.sample.whosings.usecase.user.GetCurrentUserUseCase
import com.sample.whosings.usecase.tracks.GetTracksUseCase
import com.sample.whosings.usecase.user.LogoutUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val getCurrentUserUseCase: GetCurrentUserUseCase, private val logoutUserUseCase: LogoutUserUseCase, private val getTracksUseCase: GetTracksUseCase) : ViewModel() {

    val currentUser = MutableLiveData<User?>()
    val logoutLiveData = MutableLiveData<Boolean>()
    val trackList = MutableLiveData<List<Track>>()

    fun getCurrentUser(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                currentUser.postValue(getCurrentUserUseCase.execute())
            }
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

    fun logoutUser(){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                logoutLiveData.postValue(logoutUserUseCase.execute())
            }
        }
    }
}