package com.example.theshowhub.stubbers

import com.example.theshowhub.api.MovieApiResponse

object MovieApiResponseStubber {

    fun createDummyInstance(): MovieApiResponse = MovieApiResponse(
            page = 1,
            showResponses = ShowResponseStubber.createInstanceList(),
            lastPage = 10,
            registryCount = 1900
    )

}