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
        SortOption.AZ -> shows.sortedBy { it.name }
        SortOption.ZA -> shows.sortedByDescending { it.name }
    }

}