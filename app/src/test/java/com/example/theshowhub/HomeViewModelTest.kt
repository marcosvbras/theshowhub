package com.example.theshowhub

import com.example.theshowhub.helpers.LiveDataTest
import io.mockk.mockk
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested

class HomeViewModelTest: LiveDataTest() {

    private val homeInteractorMock = mockk<HomeInteractor>()

    @Nested
    @DisplayName("Given A Top Rated Show Retrieving")
    inner class GivenATopRatedShowRetrieving {

    }

}