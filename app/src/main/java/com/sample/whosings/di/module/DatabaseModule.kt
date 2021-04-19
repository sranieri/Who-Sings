package com.sample.whosings.di.module

import android.app.Application
import androidx.room.Room
import com.sample.whosings.db.UsersDatabase
import com.sample.whosings.db.dao.UserDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): UsersDatabase {
       return Room.databaseBuilder(application, UsersDatabase::class.java, UsersDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: UsersDatabase): UserDao {
        return  database.userDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}