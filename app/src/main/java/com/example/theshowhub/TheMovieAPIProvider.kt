package com.example.theshowhub

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TheMovieAPIProvider {

    fun providesApi(gsonConverterFactory: GsonConverterFactory): TheMovieAPI = Retrofit.Builder()
        .client(createHttpClient())
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(BuildConfig.THE_MOVIE_API)
        .build()
        .create(TheMovieAPI::class.java)

    private fun createHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(createRequestInterceptor())
        .addInterceptor(createLogInterceptor())
        .build()

    private fun createRequestInterceptor(): Interceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder().build()
        chain.proceed(requestBuilder)
    }

    private fun createLogInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

}