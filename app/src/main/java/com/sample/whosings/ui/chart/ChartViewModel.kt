package com.sample.whosings.ui.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.whosings.db.model.User
import com.sample.whosings.usecase.chart.GetChartUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChartViewModel(val getChartUseCase: GetChartUseCase) : ViewModel() {

    val chartLiveData = MutableLiveData<List<User>>()

    fun getChart(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                chartLiveData.postValue(getChartUseCase.execute())
            }
        }
    }
}