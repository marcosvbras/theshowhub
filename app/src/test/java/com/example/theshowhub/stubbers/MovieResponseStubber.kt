package com.example.theshowhub.stubbers

import com.example.theshowhub.MovieResponse

object MovieResponseStubber {

    fun createInstanceList(quantity: Int = 5): List<MovieResponse> {
        val movieResponses = mutableListOf<MovieResponse>()

        for (index in 1..quantity) {
            movieResponses.add(
                    MovieResponse(
                            backdropPath = "https://backdrop.path.com/$index",
                            firstAirDate = "2004-05-10",
                            genreIds = listOf(index, index * 2),
                            id = index,
                            name = "Stubbed Name $index",
                            originCountry = listOf("US"),
                            originalLanguage = "en",
                            originalName = "Stubbed Original Name $index",
                            overview = "Overview $index",
                            popularity = index.toFloat(),
                            posterPath = "/$index.jpg",
                            voteAverage = index.toFloat(),
                            voteCount = index
                    )
            )
        }

        return movieResponses
    }

    fun createInstanceListWithNullValues(quantity: Int = 5): List<MovieResponse> {
        val movieResponses = mutableListOf<MovieResponse>()

        for (index in 1..quantity) {
            movieResponses.add(
                    MovieResponse(
                            backdropPath = null,
                            firstAirDate = null,
                            genreIds = null,
                            id = null,
                            name = null,
                            originCountry = null,
                            originalLanguage = null,
                            originalName = null,
                            overview = null,
                            popularity = null,
                            posterPath = null,
                            voteAverage = null,
                            voteCount = null
                    )
            )
        }

        return movieResponses
    }

}