package com.example.theshowhub.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theshowhub.utils.Result
import com.example.theshowhub.api.Show
import com.example.theshowhub.home.business.HomeInteractor
import com.example.theshowhub.home.business.SortOption
import com.example.theshowhub.utils.ThreadContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
        private val homeInteractor: HomeInteractor,
        private val threadContextProvider: ThreadContextProvider,
        private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val homeViewStateLiveData by lazy { MutableLiveData<HomeViewState>() }

    fun getHomeViewStateLiveData(): LiveData<HomeViewState> = homeViewStateLiveData

    fun fetchTopRatedShows() {
        val restoredShows = restoreShowsFromSavedState()

        if (restoredShows.isNotEmpty())
            homeViewStateLiveData.value = HomeViewState.CachedShowsRestored(restoredShows)
        else
            fetchShowsFromServer()
    }

    fun sortShows(shows: List<Show>, sortOption: SortOption) {
        homeViewStateLiveData.value = HomeViewState.LoadingOn

        viewModelScope.launch {
            val sortedShows = homeInteractor.sortBy(shows, sortOption)

            withContext(threadContextProvider.ui) {
                storeShowsIntoSavedState(sortedShows)
                homeViewStateLiveData.value = HomeViewState.LoadingOff
                homeViewStateLiveData.value = HomeViewState.SortedList(sortedShows)
            }
        }
    }

    private fun fetchShowsFromServer() {
        homeViewStateLiveData.value = HomeViewState.LoadingOn

        viewModelScope.launch {
            val result = homeInteractor.fetchTopRatedShows()
            homeViewStateLiveData.value = HomeViewState.LoadingOff
            withContext(threadContextProvider.ui) { handleShowResult(result) }
        }
    }

    private fun handleShowResult(result: Result<List<Show>>) = when(result) {
        is Result.Success -> {
            storeShowsIntoSavedState(result.data)
            homeViewStateLiveData.value = HomeViewState.SuccessfulListFetching(result.data)
        }
        is Result.Error ->
            homeViewStateLiveData.value = HomeViewState.FailedListFetching(result.exception)
    }

    private fun restoreShowsFromSavedState(): List<Show> =
        savedStateHandle.get<List<Show>>(SAVED_SHOW_STATE_KEY) ?: listOf()

    private fun storeShowsIntoSavedState(shows: List<Show>) =
        savedStateHandle.set(SAVED_SHOW_STATE_KEY, shows)

    companion object {
        const val SAVED_SHOW_STATE_KEY = "savedShowState"
    }

}