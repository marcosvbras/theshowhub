package com.example.theshowhub

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    viewModel { HomeViewModel(get(), get()) }
    factory { ThreadContextProvider() }
    factory { HomeInteractor(get(), get()) }
    factory { HomeRepository(get(), get()) }
    factory { GsonConverterFactory.create() }
    factory { TheMovieAPIProvider.providesApi(get()) }
    factory { ShowMapper(get()) }
    factory { DateFormatter() }
}