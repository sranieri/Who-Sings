package com.sample.whosings.di.module

import com.sample.whosings.ui.login.LoginViewModel
import com.sample.whosings.ui.chart.ChartViewModel
import com.sample.whosings.ui.home.HomeViewModel
import com.sample.whosings.ui.quiz.QuizFinishedViewModel
import com.sample.whosings.ui.quiz.QuizViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { QuizFinishedViewModel(get())}

    viewModel { QuizViewModel(get(), get(), get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ChartViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}