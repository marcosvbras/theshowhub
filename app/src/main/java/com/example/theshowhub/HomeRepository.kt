package com.example.theshowhub

class HomeRepository(private val showMapper: ShowMapper, private val theMovieAPI: TheMovieAPI) {

    suspend fun fetchTopRatedShows(): List<Show> {
        val movieApiResponse = theMovieAPI.fetchTopRatedShows(API_KEY, DEFAULT_LANGUAGE, page = 1)

        return showMapper.mapToDomainList(movieApiResponse.showResponses)
    }

    companion object {
        const val API_KEY = "25a8f80ba018b52efb64f05140f6b43c"
        const val DEFAULT_LANGUAGE = "en-US"
    }

}