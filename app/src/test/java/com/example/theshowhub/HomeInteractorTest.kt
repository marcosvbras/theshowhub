package com.example.theshowhub

import com.example.theshowhub.stubbers.ShowStubber
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class HomeInteractorTest {

    private val homeRepositoryMock = mockk<HomeRepository>()
    private val homeInteractor = HomeInteractor(homeRepositoryMock)

    @Nested
    @DisplayName("Given A Top Rated Show Retrieving")
    inner class GivenATopRatedShowRetrieving {

        @Test
        fun `WHEN the repository returns a show list it SHOULD return a success result`() {
            val showListStub = ShowStubber.createInstanceList()

            coEvery { homeRepositoryMock.fetchTopRatedShows() } returns showListStub

            val result = runBlocking { homeInteractor.fetchTopRatedShows() as Result.Success }

            assertEquals(showListStub, result.data)
        }

        @Test
        fun `WHEN the repository throws an exception it SHOULD return an error result`() {
            val exceptionStub = Exception()

            coEvery { homeRepositoryMock.fetchTopRatedShows() } throws exceptionStub

            val result = runBlocking { homeInteractor.fetchTopRatedShows() as Result.Error }

            assertEquals(exceptionStub, result.exception)
        }

    }

}