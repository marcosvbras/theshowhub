package com.example.theshowhub.home

import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.example.theshowhub.api.Show
import com.example.theshowhub.utils.Result
import com.example.theshowhub.helpers.LiveDataTest
import com.example.theshowhub.helpers.TestThreadContextProvider
import com.example.theshowhub.home.HomeViewModel.Companion.SAVED_SHOW_STATE_KEY
import com.example.theshowhub.stubbers.ShowStubber
import io.mockk.every
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verifySequence
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class HomeViewModelTest: LiveDataTest() {

    private val homeInteractorMock = mockk<HomeInteractor>()
    private val savedStateHandleMock = mockk<SavedStateHandle>(relaxed = true)
    private val homeViewModel = HomeViewModel(
        homeInteractorMock, TestThreadContextProvider(), savedStateHandleMock
    )
    private val homeViewStateObserver = mockk<Observer<HomeViewState>>(relaxed = true)
    private val loadingOnSlot = slot<HomeViewState.LoadingOn>()
    private val loadingOffSlot = slot<HomeViewState.LoadingOff>()

    @BeforeEach
    fun setUp() = homeViewModel.getHomeViewStateLiveData().observeForever(homeViewStateObserver)

    @Nested
    @DisplayName("Given A Top Rated Show Retrieving")
    inner class GivenATopRatedShowRetrieving {

        @Test
        fun `WHEN the saved state handle has stored items it SHOULD publish them as CachedShowsRestored view state`() {
            val cachedShowListStub = ShowStubber.createInstanceList()
            val cachedShowsRestoredSlot = slot<HomeViewState.CachedShowsRestored>()

            every {
                savedStateHandleMock.get<List<Show>>(SAVED_SHOW_STATE_KEY)
            } returns cachedShowListStub

            homeViewModel.fetchTopRatedShows()

            verifySequence {
                savedStateHandleMock.get<List<Show>>(SAVED_SHOW_STATE_KEY)
                homeViewStateObserver.onChanged(capture(cachedShowsRestoredSlot))
            }

            assertEquals(cachedShowsRestoredSlot.captured.shows, cachedShowListStub)
        }

        @Test
        fun `WHEN a success result is returned it SHOULD update live data with a complete success view state`() {
            val showListStub = ShowStubber.createInstanceList()
            val successResultStub = Result.Success(showListStub)
            val successfulListFetchingSlot = slot<HomeViewState.SuccessfulListFetching>()

            every { savedStateHandleMock.get<List<Show>>(SAVED_SHOW_STATE_KEY) } returns listOf()
            coEvery { homeInteractorMock.fetchTopRatedShows() } returns successResultStub

            homeViewModel.fetchTopRatedShows()

            verifySequence {
                savedStateHandleMock.get<List<Show>>(SAVED_SHOW_STATE_KEY)
                homeViewStateObserver.onChanged(capture(loadingOnSlot))
                homeViewStateObserver.onChanged(capture(loadingOffSlot))
                savedStateHandleMock.set(SAVED_SHOW_STATE_KEY, showListStub)
                homeViewStateObserver.onChanged(capture(successfulListFetchingSlot))
            }

            assertEquals(successfulListFetchingSlot.captured.shows, showListStub)
        }

        @Test
        fun `WHEN an error result is returned it SHOULD update live data with a complete error view state`() {
            val exceptionStub = Exception()
            val errorResultStub = Result.Error(exceptionStub)
            val failedListFetchingSlot = slot<HomeViewState.FailedListFetching>()

            every { savedStateHandleMock.get<List<Show>>(SAVED_SHOW_STATE_KEY) } returns listOf()
            coEvery { homeInteractorMock.fetchTopRatedShows() } returns errorResultStub

            homeViewModel.fetchTopRatedShows()

            verifySequence {
                savedStateHandleMock.get<List<Show>>(SAVED_SHOW_STATE_KEY)
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

            coEvery { homeInteractorMock.sortBy(showListStub, sortOptionStub) } returns showListStub

            homeViewModel.sortShows(showListStub, sortOptionStub)

            verifySequence {
                homeViewStateObserver.onChanged(capture(loadingOnSlot))
                savedStateHandleMock.set(SAVED_SHOW_STATE_KEY, showListStub)
                homeViewStateObserver.onChanged(capture(loadingOffSlot))
                homeViewStateObserver.onChanged(capture(sortedListSlot))
            }

            assertEquals(showListStub, sortedListSlot.captured.shows)
        }

    }

}