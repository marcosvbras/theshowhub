package com.example.theshowhub.api

import com.example.theshowhub.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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
        .build()

    private fun createRequestInterceptor(): Interceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder().build()
        chain.proceed(requestBuilder)
    }

}