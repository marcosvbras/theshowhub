package com.example.theshowhub.api

data class Show(
        val id: Int,
        val name: String,
        val posterPath: String,
        val voteAverage: Float,
        val airDate: String,
        val formattedAirDate: String
)