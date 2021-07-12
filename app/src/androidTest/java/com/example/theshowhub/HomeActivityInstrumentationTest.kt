package com.example.theshowhub

import org.junit.Test

class HomeActivityInstrumentationTest {

    @Test
    fun whenViewIsInLoadingOnState_shouldShowProgressBarOnly() {
        homeRobot {
            prepare {
                showScreen()
            } act {
                triggerLoadingOnViewState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerLoadingOnViewState()
                triggerLoadingOffViewState()
            } check {
                checkInvisibleLoading()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

    @Test
    fun whenViewIsInSuccessfulFetchingState_shouldPresentSortingControlsAndList() {
        homeRobot {
            prepare {
                showScreen()
            } act {
                triggerLoadingOnViewState()
                triggerLoadingOffViewState()
                triggerSuccessfulListFetchingViewState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerConnectionErrorState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerGenericErrorState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByWorstVoted()
                triggerSortedShowListState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByBestVoted()
                triggerSortedShowListState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByTitleAtoZ()
                triggerSortedShowListState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByTitleZtoA()
                triggerSortedShowListState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByAirDateNewestToOldest()
                triggerSortedShowListState()
            } check {
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
            prepare {
                showScreen()
            } act {
                triggerSuccessfulListFetchingViewState()
                sortByAirDateOldestToNewest()
                triggerSortedShowListState()
            } check {
                checkSortByAirDateOldestToNewest()
                checkVisibleShowList()
            } finish {
                closeScreenAndReleaseResources()
            }
        }
    }

}