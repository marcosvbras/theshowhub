package com.example.theshowhub

class HomeInteractor(private val homeRepository: HomeRepository) {

    suspend fun fetchTopRatedShows(): Result<List<Show>> = try {
        Result.Success(homeRepository.fetchTopRatedShows())
    } catch (exception: Exception) {
        Result.Error(exception)
    }

}