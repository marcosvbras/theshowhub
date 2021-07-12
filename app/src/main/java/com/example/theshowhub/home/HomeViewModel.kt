package com.example.theshowhub.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theshowhub.utils.Result
import com.example.theshowhub.api.Show
import com.example.theshowhub.utils.ThreadContextProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val homeInteractor: HomeInteractor,
    private val threadContextProvider: ThreadContextProvider
): ViewModel() {

    private val homeViewStateLiveData by lazy { MutableLiveData<HomeViewState>() }

    fun getHomeViewStateLiveData(): LiveData<HomeViewState> = homeViewStateLiveData

    fun fetchTopRatedShows() {
        homeViewStateLiveData.value = HomeViewState.LoadingOn

        viewModelScope.launch {
            val result = homeInteractor.fetchTopRatedShows()
            homeViewStateLiveData.value = HomeViewState.LoadingOff
            withContext(threadContextProvider.ui) { handleShowResult(result) }
        }
    }

    fun sortShows(shows: List<Show>, sortOption: SortOption) {
        homeViewStateLiveData.value = HomeViewState.LoadingOn

        viewModelScope.launch {
            withContext(threadContextProvider.ui) {
                val sortedShows = homeInteractor.sortBy(shows, sortOption)
                homeViewStateLiveData.value = HomeViewState.LoadingOff
                homeViewStateLiveData.value = HomeViewState.SortedList(sortedShows)
            }
        }
    }

    private fun handleShowResult(result: Result<List<Show>>) = when(result) {
        is Result.Success ->
            homeViewStateLiveData.value = HomeViewState.SuccessfulListFetching(result.data)
        is Result.Error ->
            homeViewStateLiveData.value = HomeViewState.FailedListFetching(result.exception)
    }

}