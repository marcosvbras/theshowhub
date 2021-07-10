package com.example.theshowhub

sealed class HomeViewState {
    object LoadingOn: HomeViewState()
    object LoadingOff : HomeViewState()
    class SuccessfulListFetching(val movies: List<Movie>): HomeViewState()
    class FailedListFetching(val exception: Exception): HomeViewState()
}
