package com.example.theshowhub.home

import com.example.theshowhub.HomeInteractor
import com.example.theshowhub.HomeRepository
import com.example.theshowhub.Result
import com.example.theshowhub.SortOption
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

    @Nested
    @DisplayName("Given A Show List Sorting")
    inner class GivenAShowListSorting {

        @Test
        fun `WHEN it passed best voted option it SHOULD return a descending list sorted by votes`() {
            val inputShowList = listOf(
                ShowStubber.createDummyInstance().copy(voteAverage = 6F),
                ShowStubber.createDummyInstance().copy(voteAverage = 7.5F),
                ShowStubber.createDummyInstance().copy(voteAverage = 9F)
            )

            val expectedShowList = listOf(
                ShowStubber.createDummyInstance().copy(voteAverage = 9F),
                ShowStubber.createDummyInstance().copy(voteAverage = 7.5F),
                ShowStubber.createDummyInstance().copy(voteAverage = 6F)
            )

            val outputShowList = homeInteractor.sortBy(inputShowList, SortOption.BestVoted)

            assertEquals(inputShowList.size, outputShowList.size)
            assertEquals(expectedShowList, outputShowList)
        }

        @Test
        fun `WHEN it passed worst voted option it SHOULD return an ascending list sorted by votes`() {
            val inputShowList = listOf(
                ShowStubber.createDummyInstance().copy(voteAverage = 10F),
                ShowStubber.createDummyInstance().copy(voteAverage = 6F),
                ShowStubber.createDummyInstance().copy(voteAverage = 7F)
            )

            val expectedShowList = listOf(
                ShowStubber.createDummyInstance().copy(voteAverage = 6F),
                ShowStubber.createDummyInstance().copy(voteAverage = 7F),
                ShowStubber.createDummyInstance().copy(voteAverage = 10F)
            )

            val outputShowList = homeInteractor.sortBy(inputShowList, SortOption.WorstVoted)

            assertEquals(inputShowList.size, outputShowList.size)
            assertEquals(expectedShowList, outputShowList)
        }

        @Test
        fun `WHEN it passed title az option it SHOULD return an ascending list sorted by name`() {
            val inputShowList = listOf(
                ShowStubber.createDummyInstance().copy(name = "EEE"),
                ShowStubber.createDummyInstance().copy(name = "AAA"),
                ShowStubber.createDummyInstance().copy(name = "BBB")
            )

            val expectedShowList = listOf(
                ShowStubber.createDummyInstance().copy(name = "AAA"),
                ShowStubber.createDummyInstance().copy(name = "BBB"),
                ShowStubber.createDummyInstance().copy(name = "EEE")
            )

            val outputShowList = homeInteractor.sortBy(inputShowList, SortOption.TitleAZ)

            assertEquals(inputShowList.size, outputShowList.size)
            assertEquals(expectedShowList, outputShowList)
        }

        @Test
        fun `WHEN it passed title za option it SHOULD return a descending list sorted by name`() {
            val inputShowList = listOf(
                ShowStubber.createDummyInstance().copy(name = "CCC"),
                ShowStubber.createDummyInstance().copy(name = "LLL"),
                ShowStubber.createDummyInstance().copy(name = "LLL")
            )

            val expectedShowList = listOf(
                ShowStubber.createDummyInstance().copy(name = "LLL"),
                ShowStubber.createDummyInstance().copy(name = "LLL"),
                ShowStubber.createDummyInstance().copy(name = "CCC")
            )

            val outputShowList = homeInteractor.sortBy(inputShowList, SortOption.TitleZA)

            assertEquals(inputShowList.size, outputShowList.size)
            assertEquals(expectedShowList, outputShowList)
        }

    }

}