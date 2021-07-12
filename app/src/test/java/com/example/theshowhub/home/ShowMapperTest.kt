package com.example.theshowhub.home

import com.example.theshowhub.DateFormatter
import com.example.theshowhub.DateFormatter.Companion.MMM_YYYY
import com.example.theshowhub.DateFormatter.Companion.YYYY_MM_DD
import com.example.theshowhub.Show
import com.example.theshowhub.ShowMapper
import com.example.theshowhub.ShowMapper.Companion.IMAGE_PATH_DOMAIN
import com.example.theshowhub.ShowResponse
import com.example.theshowhub.stubbers.ShowResponseStubber
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ShowMapperTest {

    private val dateFormatterMock = mockk<DateFormatter>()
    private val showMapper = ShowMapper(dateFormatterMock)
    val formattedAirDateStub = "July 2021"

    @Nested
    @DisplayName("Given A Domain List Mapping")
    inner class GivenADomainListMapping {

        @Test
        fun `WHEN the movie responses have valid values it SHOULD return an equivalent movie list`() {
            val movieResponseListStub = ShowResponseStubber.createInstanceList()

            movieResponseListStub.forEach { movieResponse ->
                every {
                    dateFormatterMock.format(movieResponse.airDate ?: "", YYYY_MM_DD, MMM_YYYY)
                } returns formattedAirDateStub
            }

            val moviesOutput = showMapper.mapToDomainList(movieResponseListStub)

            assertEquals(movieResponseListStub.size, moviesOutput.size)

            for (index in moviesOutput.indices)
                assertSameValues(movieResponseListStub[index], moviesOutput[index])
        }

        @Test
        fun `WHEN the movie responses have null values it SHOULD return a movie list with default values`() {
            val movieResponseListStub = ShowResponseStubber.createInstanceListWithNullValues()

            movieResponseListStub.forEach { movieResponse ->
                every {
                    dateFormatterMock.format(movieResponse.airDate ?: "", YYYY_MM_DD, MMM_YYYY)
                } returns ""
            }

            val moviesOutput = showMapper.mapToDomainList(movieResponseListStub)

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
        assertEquals(showResponse.airDate, show.airDate)
        assertEquals(formattedAirDateStub, show.formattedAirDate)
    }

    private fun assertDefaultValues(show: Show) {
        assertEquals(0, show.id)
        assertEquals(0F, show.voteAverage)
        assertTrue(show.name.isEmpty())
        assertTrue(show.posterPath.isEmpty())
        assertTrue(show.airDate.isEmpty())
        assertTrue(show.formattedAirDate.isEmpty())
    }

}