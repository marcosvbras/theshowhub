package com.example.theshowhub

class ShowMapper {

    companion object {
        const val IMAGE_PATH_DOMAIN = "https://image.tmdb.org/t/p/w500"
    }

    private fun mapToDomain(showResponse: ShowResponse): Show = Show(
        id = showResponse.id ?: 0,
        name = showResponse.name ?: "",
        posterPath = showResponse.posterPath?.run {
            IMAGE_PATH_DOMAIN + showResponse.posterPath
        } ?: "",
        voteAverage = showResponse.voteAverage ?: 0F,
        firstAirDate = showResponse.firstAirDate ?: ""
    )

    fun mapToDomainList(showRespons: List<ShowResponse>): List<Show> {
        return showRespons.map { mapToDomain(it) }
    }

}