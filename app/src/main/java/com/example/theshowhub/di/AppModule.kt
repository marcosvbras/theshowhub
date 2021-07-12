package com.example.theshowhub.di

import com.example.theshowhub.api.TheMovieAPIProvider
import com.example.theshowhub.home.HomeInteractor
import com.example.theshowhub.home.HomeRepository
import com.example.theshowhub.home.HomeViewModel
import com.example.theshowhub.home.ShowMapper
import com.example.theshowhub.utils.DateFormatter
import com.example.theshowhub.utils.ThreadContextProvider
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