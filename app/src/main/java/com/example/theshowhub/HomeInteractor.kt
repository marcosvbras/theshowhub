package com.example.theshowhub

class HomeInteractor(private val homeRepository: HomeRepository) {

    suspend fun fetchTopRatedShows(): Result<List<Movie>> = try {
        Result.Success(homeRepository.fetchTopRatedShows())
    } catch (exception: Exception) {
        Result.Error(exception)
    }

}