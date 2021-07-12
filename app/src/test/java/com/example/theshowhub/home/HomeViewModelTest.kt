package com.example.theshowhub.home

import androidx.lifecycle.Observer
import com.example.theshowhub.HomeInteractor
import com.example.theshowhub.HomeViewModel
import com.example.theshowhub.HomeViewState
import com.example.theshowhub.Result
import com.example.theshowhub.SortOption
import com.example.theshowhub.helpers.LiveDataTest
import com.example.theshowhub.helpers.TestThreadContextProvider
import com.example.theshowhub.stubbers.ShowStubber
import io.mockk.every
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class HomeViewModelTest: LiveDataTest() {

    private val homeInteractorMock = mockk<HomeInteractor>()
    private val homeViewModel = HomeViewModel(homeInteractorMock, TestThreadContextProvider())
    private val homeViewStateObserver = mockk<Observer<HomeViewState>>(relaxed = true)
    private val loadingOnSlot = slot<HomeViewState.LoadingOn>()
    private val loadingOffSlot = slot<HomeViewState.LoadingOff>()

    @BeforeEach
    fun setUp() = homeViewModel.getHomeViewStateLiveData().observeForever(homeViewStateObserver)

    @Nested
    @DisplayName("Given A Top Rated Show Retrieving")
    inner class GivenATopRatedShowRetrieving {

        @Test
        fun `WHEN a success result is returned it SHOULD update live data with a complete success view state`() {
            val movieListStub = ShowStubber.createInstanceList()
            val successResultStub = Result.Success(movieListStub)
            val successfulListFetchingSlot = slot<HomeViewState.SuccessfulListFetching>()

            coEvery { homeInteractorMock.fetchTopRatedShows() } returns successResultStub

            homeViewModel.fetchTopRatedShows()

            verifySequence {
                homeViewStateObserver.onChanged(capture(loadingOnSlot))
                homeViewStateObserver.onChanged(capture(loadingOffSlot))
                homeViewStateObserver.onChanged(capture(successfulListFetchingSlot))
            }

            assertEquals(successfulListFetchingSlot.captured.shows, movieListStub)
        }

        @Test
        fun `WHEN an error result is returned it SHOULD update live data with a complete error view state`() {
            val exceptionStub = Exception()
            val errorResultStub = Result.Error(exceptionStub)
            val failedListFetchingSlot = slot<HomeViewState.FailedListFetching>()

            coEvery { homeInteractorMock.fetchTopRatedShows() } returns errorResultStub

            homeViewModel.fetchTopRatedShows()

            verifySequence {
                homeViewStateObserver.onChanged(capture(loadingOnSlot))
                homeViewStateObserver.onChanged(capture(loadingOffSlot))
                homeViewStateObserver.onChanged(capture(failedListFetchingSlot))
            }

            assertEquals(failedListFetchingSlot.captured.exception, exceptionStub)
        }

    }

    @Nested
    @DisplayName("Given A Show List Sorting")
    inner class GivenAShowListSorting {

        @Test
        fun `SHOULD update live data with sorted shows`() {
            val showListStub = ShowStubber.createInstanceList()
            val sortOptionStub = SortOption.WorstVoted
            val sortedListSlot = slot<HomeViewState.SortedList>()

            every { homeInteractorMock.sortBy(showListStub, sortOptionStub) } returns showListStub

            homeViewModel.sortShows(showListStub, sortOptionStub)

            verify {
                homeViewStateObserver.onChanged(capture(sortedListSlot))
            }

            assertEquals(showListStub, sortedListSlot.captured.shows)
        }

    }

}