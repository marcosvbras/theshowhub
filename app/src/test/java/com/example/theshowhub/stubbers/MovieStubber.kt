package com.example.theshowhub.stubbers

import com.example.theshowhub.Movie

object MovieStubber {

    fun createInstanceList(quantity: Int = 5): List<Movie> {
        val movies = mutableListOf<Movie>()

        for (index in 1..quantity) {
            movies.add(
                    Movie(
                            id = index,
                            name = "Stubbed Name $index",
                            posterPath = "https://poster.path.com/$index"
                    )
            )
        }

        return movies
    }

}