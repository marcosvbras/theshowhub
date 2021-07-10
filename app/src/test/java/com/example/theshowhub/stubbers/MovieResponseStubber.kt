package com.example.theshowhub.stubbers

import com.example.theshowhub.ShowResponse

object MovieResponseStubber {

    fun createInstanceList(quantity: Int = 5): List<ShowResponse> {
        val movieResponses = mutableListOf<ShowResponse>()

        for (index in 1..quantity) {
            movieResponses.add(
                    ShowResponse(
                        id = index,
                        name = "Stubbed Name $index",
                        posterPath = "/$index.jpg",
                        voteAverage = index.toFloat(),
                        firstAirDate = "2004-05-10"
                    )
            )
        }

        return movieResponses
    }

    fun createInstanceListWithNullValues(quantity: Int = 5): List<ShowResponse> {
        val movieResponses = mutableListOf<ShowResponse>()

        for (index in 1..quantity) {
            movieResponses.add(
                    ShowResponse(
                        id = null,
                        name = null,
                        posterPath = null,
                        voteAverage = null,
                        firstAirDate = null
                    )
            )
        }

        return movieResponses
    }

}