package com.example.theshowhub.home

import com.example.theshowhub.api.Show

sealed class HomeViewState {
    object LoadingOn: HomeViewState()
    object LoadingOff : HomeViewState()
    class SuccessfulListFetching(val shows: List<Show>): HomeViewState()
    class FailedListFetching(val exception: Exception): HomeViewState()
    class SortedList(val shows: List<Show>): HomeViewState()
}
