package com.example.theshowhub

class HomeInteractor(private val homeRepository: HomeRepository) {

    suspend fun fetchTopRatedShows(): Result<List<Show>> = try {
        Result.Success(homeRepository.fetchTopRatedShows())
    } catch (exception: Exception) {
        Result.Error(exception)
    }

    fun sortBy(shows: List<Show>, sortOption: SortOption): List<Show> = when(sortOption) {
        SortOption.BestVoted -> shows.sortedByDescending { it.voteAverage }
        SortOption.WorstVoted -> shows.sortedBy { it.voteAverage }
        SortOption.TitleAZ -> shows.sortedBy { it.name }
        SortOption.TitleZA -> shows.sortedByDescending { it.name }
        SortOption.AirDateNewest -> shows.sortedByDescending { it.airDate }
        SortOption.AirDateOldest -> shows.sortedBy { it.airDate }
    }

}