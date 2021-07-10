package com.example.theshowhub.stubbers

import com.example.theshowhub.Show

object ShowStubber {

    fun createInstanceList(quantity: Int = 5): List<Show> {
        val shows = mutableListOf<Show>()

        for (index in 1..quantity) {
            shows.add(
                    Show(
                        id = index,
                        name = "Stubbed Name $index",
                        posterPath = "https://poster.path.com/$index",
                        voteAverage = index.toFloat(),
                        firstAirDate = "2020-01-$index"
                    )
            )
        }

        return shows
    }

}