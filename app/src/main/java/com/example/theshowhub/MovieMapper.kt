package com.example.theshowhub

class MovieMapper {

    companion object {
        const val IMAGE_PATH_DOMAIN = "https://image.tmdb.org/t/p/w500"
    }

    private fun mapToDomain(movieResponse: MovieResponse): Movie = Movie(
            id = movieResponse.id ?: 0,
            name = movieResponse.name ?: "",
            posterPath = movieResponse.posterPath?.run {
                IMAGE_PATH_DOMAIN + movieResponse.posterPath
            } ?: ""
    )

    fun mapToDomainList(movieResponses: List<MovieResponse>): List<Movie> {
        return movieResponses.map { mapToDomain(it) }
    }

}