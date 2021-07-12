package com.example.theshowhub.api

import retrofit2.http.GET
import retrofit2.http.Query

interface TheMovieAPI {

    @GET("3/tv/top_rated")
    suspend fun fetchTopRatedShows(
        @Query(API_KEY_QUERY_PARAMETER) apiKey: String,
        @Query(LANGUAGE_QUERY_PARAMETER) language: String,
        @Query(PAGE_QUERY_PARAMETER) page: Int
    ): MovieApiResponse

    companion object {
        private const val API_KEY_QUERY_PARAMETER = "api_key"
        private const val LANGUAGE_QUERY_PARAMETER = "language"
        private const val PAGE_QUERY_PARAMETER = "page"
    }

}