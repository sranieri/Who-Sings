package com.sample.whosings.di.module

import com.sample.whosings.data.repository.MusixMatchRepository
import com.sample.whosings.data.repository.UsersRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MusixMatchRepository(get())
    }
    single {
        UsersRepository(get())
    }
}