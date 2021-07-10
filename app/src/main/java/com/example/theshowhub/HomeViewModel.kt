package com.example.theshowhub

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

            withContext(threadContextProvider.ui) { handleMovieResult(result) }
        }
    }

    private fun handleMovieResult(result: Result<List<Movie>>) = when(result) {
        is Result.Success ->
            homeViewStateLiveData.value = HomeViewState.SuccessfulListFetching(result.data)
        is Result.Error ->
            homeViewStateLiveData.value = HomeViewState.FailedListFetching(result.exception)
    }

}