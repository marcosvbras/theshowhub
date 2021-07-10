package com.example.theshowhub

import com.example.theshowhub.MovieMapper.Companion.IMAGE_PATH_DOMAIN
import com.example.theshowhub.stubbers.MovieResponseStubber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MovieMapperTest {

    private val movieMapper = MovieMapper()

    @Nested
    @DisplayName("Given A Domain List Mapping")
    inner class GivenADomainListMapping {

        @Test
        fun `WHEN the movie responses have valid values it SHOULD return an equivalent movie list`() {
            val movieResponseListStub = MovieResponseStubber.createInstanceList()

            val moviesOutput = movieMapper.mapToDomainList(movieResponseListStub)

            assertEquals(movieResponseListStub.size, moviesOutput.size)

            for (index in moviesOutput.indices)
                assertSameValues(movieResponseListStub[index], moviesOutput[index])
        }

        @Test
        fun `WHEN the movie responses have null values it SHOULD return a movie list with default values`() {
            val movieResponseListStub = MovieResponseStubber.createInstanceListWithNullValues()

            val moviesOutput = movieMapper.mapToDomainList(movieResponseListStub)

            assertEquals(movieResponseListStub.size, moviesOutput.size)

            for (index in moviesOutput.indices)
                assertDefaultValues(moviesOutput[index])
        }

    }

    private fun assertSameValues(movieResponse: MovieResponse, movie: Movie) {
        assertEquals(movieResponse.id, movie.id)
        assertEquals(movieResponse.name, movie.name)
        assertEquals(IMAGE_PATH_DOMAIN + movieResponse.posterPath, movie.posterPath)
    }

    private fun assertDefaultValues(movie: Movie) {
        assertEquals(0, movie.id)
        assertTrue(movie.name.isEmpty())
        assertTrue(movie.posterPath.isEmpty())
    }

}