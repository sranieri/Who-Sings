package com.sample.whosings.di.module

import com.sample.whosings.BuildConfig
import com.sample.whosings.data.api.TracksApiHelper
import com.sample.whosings.data.api.TracksApiService
import com.sample.whosings.data.api.TracksApiHelperImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import org.koin.dsl.module

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }

    single<TracksApiHelper> {
        return@single TracksApiHelperImpl(get())
    }
}

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    OkHttpClient.Builder()
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): TracksApiService = retrofit.create(TracksApiService::class.java)