package com.example.theshowhub

import com.example.theshowhub.ShowMapper.Companion.IMAGE_PATH_DOMAIN
import com.example.theshowhub.stubbers.MovieResponseStubber
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ShowMapperTest {

    private val movieMapper = ShowMapper()

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

    private fun assertSameValues(showResponse: ShowResponse, show: Show) {
        assertEquals(showResponse.id, show.id)
        assertEquals(showResponse.name, show.name)
        assertEquals(IMAGE_PATH_DOMAIN + showResponse.posterPath, show.posterPath)
        assertEquals(showResponse.voteAverage, show.voteAverage)
        assertEquals(showResponse.firstAirDate, show.firstAirDate)
    }

    private fun assertDefaultValues(show: Show) {
        assertEquals(0, show.id)
        assertEquals(0F, show.voteAverage)
        assertTrue(show.name.isEmpty())
        assertTrue(show.posterPath.isEmpty())
        assertTrue(show.firstAirDate.isEmpty())
    }

}