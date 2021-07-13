package com.example.theshowhub.home.business

import com.example.theshowhub.utils.Result
import com.example.theshowhub.api.Show
import com.example.theshowhub.utils.ThreadContextProvider
import kotlinx.coroutines.withContext

class HomeInteractor(
        private val homeRepository: HomeRepository,
        private val threadContextProvider: ThreadContextProvider
) {

    suspend fun fetchTopRatedShows(): Result<List<Show>> = try {
        withContext(threadContextProvider.io) {
            Result.Success(homeRepository.fetchTopRatedShows())
        }
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    suspend fun sortBy(shows: List<Show>, sortOption: SortOption): List<Show> {
        return withContext(threadContextProvider.cpu) {
            when(sortOption) {
                SortOption.BestVoted -> shows.sortedByDescending { it.voteAverage }
                SortOption.WorstVoted -> shows.sortedBy { it.voteAverage }
                SortOption.TitleAZ -> shows.sortedBy { it.name }
                SortOption.TitleZA -> shows.sortedByDescending { it.name }
                SortOption.AirDateNewest -> shows.sortedByDescending { it.airDate }
                SortOption.AirDateOldest -> shows.sortedBy { it.airDate }
            }
        }
    }

}