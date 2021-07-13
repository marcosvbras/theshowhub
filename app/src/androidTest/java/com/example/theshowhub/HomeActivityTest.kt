package com.example.theshowhub

import org.junit.Test

class HomeActivityTest {

    @Test
    fun whenViewIsInLoadingOnState_shouldShowProgressBarOnly() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerLoadingOnViewState()
            } assert {
                checkVisibleLoading()
                checkHiddenSortingControls()
                checkHiddenShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenViewIsInLoadingOffState_shouldHideProgressBar() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerLoadingOnViewState()
                triggerLoadingOffViewState()
            } assert {
                checkInvisibleLoading()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenViewIsInSuccessfulFetchingState_shouldPresentSortingControlsAndList() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerLoadingOnViewState()
                triggerLoadingOffViewState()
                triggerSuccessfulListFetchingViewState()
            } assert {
                checkVisibleSortingControls()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenViewIsInConnectionErrorState_shouldPresentOnlyErrorLayoutWithConnectionErrorText() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerConnectionErrorState()
            } assert {
                checkHiddenSortingControls()
                checkHiddenShowList()
                checkInvisibleLoading()
                checkVisibleConnectionError()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenViewIsInGenericErrorState_shouldPresentOnlyErrorLayoutWithGenericErrorText() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerGenericErrorState()
            } assert {
                checkHiddenSortingControls()
                checkHiddenShowList()
                checkInvisibleLoading()
                checkVisibleGenericError()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenAListIsSortedByWorstVoted_shouldPresentTheNewList() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByWorstVoted()
                triggerSortedShowListState()
            } assert {
                checkSortByWorstVotedChange()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenAListIsSortedByBestVoted_shouldPresentTheNewList() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByBestVoted()
                triggerSortedShowListState()
            } assert {
                checkSortByBestVotedChange()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenAListIsSortedByTitleAtoZ_shouldPresentTheNewList() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByTitleAtoZ()
                triggerSortedShowListState()
            } assert {
                checkSortByTitleAtoZChange()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenAListIsSortedByTitleZtoA_shouldPresentTheNewList() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByTitleZtoA()
                triggerSortedShowListState()
            } assert {
                checkSortByTitleZtoAChange()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenAListIsSortedByAirDateNewestToOldest_shouldPresentTheNewList() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByAirDateNewestToOldest()
                triggerSortedShowListState()
            } assert {
                checkSortByAirDateNewestToOldest()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenAListIsSortedByAirDateOldestToNewest_shouldPresentTheNewList() {
        homeRobot {
            arrange {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByAirDateOldestToNewest()
                triggerSortedShowListState()
            } assert {
                checkSortByAirDateOldestToNewest()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

}