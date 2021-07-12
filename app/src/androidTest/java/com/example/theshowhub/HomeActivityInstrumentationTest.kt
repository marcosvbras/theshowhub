package com.example.theshowhub

import org.junit.Test

class HomeActivityInstrumentationTest {

    @Test
    fun whenViewIsInLoadingOnState_shouldShowProgressBarOnly() {
        prepare {

        } act {
            showScreen()
            triggerLoadingOnViewState()
        } check {
            checkVisibleLoading()
            checkHiddenSortingControls()
            checkHiddenShowList()
        }
    }

    @Test
    fun whenViewIsInLoadingOffState_shouldHideProgressBar() {
        prepare {

        } act {
            showScreen()
            triggerLoadingOnViewState()
            triggerLoadingOffViewState()
        } check {
            checkInvisibleLoading()
        }
    }

    @Test
    fun whenViewIsInSuccessfulFetchingState_shouldPresentSortingControlsAndList() {
        prepare {

        } act {
            showScreen()
            triggerLoadingOnViewState()
            triggerLoadingOffViewState()
            triggerSuccessfulListFetchingViewState()
        } check {
            checkVisibleSortingControls()
            checkVisibleShowList()
        }
    }

    @Test
    fun whenViewIsInConnectionErrorState_shouldPresentOnlyErrorLayoutWithConnectionErrorText() {
        prepare {

        } act {
            showScreen()
            triggerConnectionErrorState()
        } check {
            checkHiddenSortingControls()
            checkHiddenShowList()
            checkInvisibleLoading()
            checkVisibleConnectionError()
        }
    }

    @Test
    fun whenViewIsInGenericErrorState_shouldPresentOnlyErrorLayoutWithGenericErrorText() {
        prepare {

        } act {
            showScreen()
            triggerGenericErrorState()
        } check {
            checkHiddenSortingControls()
            checkHiddenShowList()
            checkInvisibleLoading()
            checkVisibleGenericError()
        }
    }

    @Test
    fun whenAListIsSortedByWorstVoted_shouldPresentTheNewList() {
        prepare {

        } act {
            showScreen()
            triggerSuccessfulListFetchingViewState()
            sortByWorstVoted()
            triggerSortedShowListState()
        } check {
            checkSortByWorstVotedChange()
            checkVisibleShowList()
        }
    }

    @Test
    fun whenAListIsSortedByBestVoted_shouldPresentTheNewList() {
        prepare {

        } act {
            showScreen()
            triggerSuccessfulListFetchingViewState()
            sortByBestVoted()
            triggerSortedShowListState()
        } check {
            checkSortByBestVotedChange()
            checkVisibleShowList()
        }
    }

    @Test
    fun whenAListIsSortedByTitleAtoZ_shouldPresentTheNewList() {
        prepare {

        } act {
            showScreen()
            triggerSuccessfulListFetchingViewState()
            sortByTitleAtoZ()
            triggerSortedShowListState()
        } check {
            checkSortByTitleAtoZChange()
            checkVisibleShowList()
        }
    }

    @Test
    fun whenAListIsSortedByTitleZtoA_shouldPresentTheNewList() {
        prepare {

        } act {
            showScreen()
            triggerSuccessfulListFetchingViewState()
            sortByTitleZtoA()
            triggerSortedShowListState()
        } check {
            checkSortByTitleZtoAChange()
            checkVisibleShowList()
        }
    }

    @Test
    fun whenAListIsSortedByAirDateNewestToOldest_shouldPresentTheNewList() {
        prepare {

        } act {
            showScreen()
            triggerSuccessfulListFetchingViewState()
            sortByAirDateNewestToOldest()
            triggerSortedShowListState()
        } check {
            checkSortByAirDateNewestToOldest()
            checkVisibleShowList()
        }
    }

    @Test
    fun whenAListIsSortedByAirDateOldestToNewest_shouldPresentTheNewList() {
        prepare {

        } act {
            showScreen()
            triggerSuccessfulListFetchingViewState()
            sortByAirDateOldestToNewest()
            triggerSortedShowListState()
        } check {
            checkSortByAirDateOldestToNewest()
            checkVisibleShowList()
        }
    }

}