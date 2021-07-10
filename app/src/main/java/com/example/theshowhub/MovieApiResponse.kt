package com.example.theshowhub

import com.google.gson.annotations.SerializedName

data class MovieApiResponse(
        @SerializedName("page") val page: Int,
        @SerializedName("results") val movieResponses: List<MovieResponse>,
        @SerializedName("total_pages") val lastPage: Int,
        @SerializedName("total_results") val registryCount: Int
)