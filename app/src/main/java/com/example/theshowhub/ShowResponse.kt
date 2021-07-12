package com.example.theshowhub

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("vote_average") val voteAverage: Float?,
    @SerializedName("first_air_date") val airDate: String?
)
