package com.example.theshowhub

import kotlinx.coroutines.withContext

class HomeRepository(
    private val showMapper: ShowMapper,
    private val theMovieAPI: TheMovieAPI,
    private val threadContextProvider: ThreadContextProvider
) {

    suspend fun fetchTopRatedShows(): List<Show> = withContext(threadContextProvider.io) {
        val movieApiResponse = theMovieAPI.fetchTopRatedShows(API_KEY, DEFAULT_LANGUAGE, page = 1)

        showMapper.mapToDomainList(movieApiResponse.showResponses)
    }

    companion object {
        const val API_KEY = "25a8f80ba018b52efb64f05140f6b43c"
        const val DEFAULT_LANGUAGE = "en-US"
    }

}