package com.example.theshowhub.stubbers

import com.example.theshowhub.MovieApiResponse

object MovieApiResponseStubber {

    fun createDummyInstance(): MovieApiResponse = MovieApiResponse(
            page = 1,
            showResponses = MovieResponseStubber.createInstanceList(),
            lastPage = 10,
            registryCount = 1900
    )

}