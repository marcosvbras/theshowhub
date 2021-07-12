package com.example.theshowhub.stubbers

import com.example.theshowhub.api.Show

object ShowStubber {

    fun createInstanceList(quantity: Int = 5): List<Show> {
        val shows = mutableListOf<Show>()

        for (index in 1..quantity) {
            shows.add(
                    Show(
                        id = index,
                        name = "Stubbed Name $index",
                        posterPath = "https://poster.path.com/$index.jpg",
                        voteAverage = index.toFloat(),
                        airDate = "2020-01-$index",
                        formattedAirDate = "July 2019"
                    )
            )
        }

        return shows
    }

    fun createDummyInstance(): Show = Show(
        id = 12345,
        name = "Stubbed Name",
        posterPath = "https://poster.path.com/12345566.jpg",
        voteAverage = 9F,
        airDate = "2020-01-04",
        formattedAirDate = "July 2019"
    )

}