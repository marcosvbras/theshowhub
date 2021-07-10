package com.example.theshowhub

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("poster_path") val posterPath: String
)