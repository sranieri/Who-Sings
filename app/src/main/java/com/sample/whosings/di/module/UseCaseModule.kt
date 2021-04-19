package com.sample.whosings.di.module

import com.sample.whosings.usecase.chart.GetChartUseCase
import com.sample.whosings.usecase.question.CheckQuestionUseCase
import com.sample.whosings.usecase.question.GetQuestionUseCase
import com.sample.whosings.usecase.tracks.GetLyricsUseCase
import com.sample.whosings.usecase.tracks.GetTracksUseCase
import com.sample.whosings.usecase.user.GetCurrentUserUseCase
import com.sample.whosings.usecase.user.LoginUserUseCase
import com.sample.whosings.usecase.user.LogoutUserUseCase
import com.sample.whosings.usecase.user.UpdateUserScoreUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { GetTracksUseCase(get()) }
    factory { GetLyricsUseCase(get()) }
    factory { GetCurrentUserUseCase(get()) }
    factory { LogoutUserUseCase(get()) }
    factory { LoginUserUseCase(get()) }
    factory { UpdateUserScoreUseCase(get()) }
    factory { GetChartUseCase(get()) }
    factory { GetQuestionUseCase() }
    factory { CheckQuestionUseCase() }
}