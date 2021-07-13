package com.example.theshowhub.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Show(
        val id: Int,
        val name: String,
        val posterPath: String,
        val voteAverage: Float,
        val airDate: String,
        val formattedAirDate: String
): Parcelable