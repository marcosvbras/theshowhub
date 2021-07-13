package com.example.theshowhub.home.business

import com.example.theshowhub.api.Show
import com.example.theshowhub.api.ShowResponse
import com.example.theshowhub.utils.DateFormatter
import com.example.theshowhub.utils.DateFormatter.Companion.MMM_YYYY
import com.example.theshowhub.utils.DateFormatter.Companion.YYYY_MM_DD

class ShowMapper(private val dateFormatter: DateFormatter) {

    private fun mapToDomain(showResponse: ShowResponse): Show = Show(
        id = showResponse.id ?: 0,
        name = showResponse.name ?: "",
        posterPath = showResponse.posterPath?.run {
            IMAGE_PATH_DOMAIN + showResponse.posterPath
        } ?: "",
        voteAverage = showResponse.voteAverage ?: 0F,
        airDate = showResponse.airDate ?: "",
        formattedAirDate = dateFormatter.format(
            originalDate = showResponse.airDate ?: "", inputFormat = YYYY_MM_DD, outputFormat = MMM_YYYY
        )
    )

    fun mapToDomainList(showResponses: List<ShowResponse>): List<Show> =
        showResponses.map { mapToDomain(it) }

    companion object {
        const val IMAGE_PATH_DOMAIN = "https://image.tmdb.org/t/p/w500"
    }

}