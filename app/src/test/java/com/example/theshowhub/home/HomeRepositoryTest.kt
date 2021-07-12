package com.example.theshowhub.home

import com.example.theshowhub.HomeRepository
import com.example.theshowhub.HomeRepository.Companion.API_KEY
import com.example.theshowhub.HomeRepository.Companion.DEFAULT_LANGUAGE
import com.example.theshowhub.ShowMapper
import com.example.theshowhub.TheMovieAPI
import com.example.theshowhub.stubbers.MovieApiResponseStubber
import com.example.theshowhub.stubbers.ShowStubber
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

class HomeRepositoryTest {

    private val homeMapperMock = mockk<ShowMapper>()
    private val theMovieAPIMock = mockk<TheMovieAPI>()
    private val homeRepository = HomeRepository(homeMapperMock, theMovieAPIMock)

    @Nested
    @DisplayName("Given A Top Rated Show Retrieving")
    inner class GivenATopRatedShowRetrieving {

        @Test
        fun `WHEN it the api returns a success response it SHOULD return a movie list`() {
            val movieApiResponse = MovieApiResponseStubber.createDummyInstance()
            val shows = ShowStubber.createInstanceList()

            coEvery {
                theMovieAPIMock.fetchTopRatedShows(API_KEY, DEFAULT_LANGUAGE, 1)
            } returns movieApiResponse

            every { homeMapperMock.mapToDomainList(movieApiResponse.showResponses) } returns shows

            val output = runBlocking { homeRepository.fetchTopRatedShows() }

            assertEquals(shows, output)
        }

        @Test
        fun `WHEN it the api returns an error response it SHOULD NOT handle the exception`() {
            val exceptionStub = Exception()

            coEvery {
                theMovieAPIMock.fetchTopRatedShows(API_KEY, DEFAULT_LANGUAGE, 1)
            } throws exceptionStub

            assertThrows(exceptionStub::class.java) {
                runBlocking {
                    homeRepository.fetchTopRatedShows()
                }
            }
        }

    }

}