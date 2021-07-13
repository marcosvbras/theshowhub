package com.example.theshowhub

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.example.theshowhub.api.Show
import com.example.theshowhub.utils.ConnectionException
import com.example.theshowhub.home.HomeActivity
import com.example.theshowhub.home.HomeViewModel
import com.example.theshowhub.home.HomeViewState
import com.example.theshowhub.home.SortOption
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun homeRobot(instance: HomeRobot.() -> Unit) = HomeRobot().apply(instance)

class HomeRobot {

    private val homeViewModel: HomeViewModel = mockk(relaxed = true)
    private val viewStateLiveData = MutableLiveData<HomeViewState>()
    private val showListStub = createInstanceList()
    private val sortedShowListStub = createInstanceList()
    lateinit var activityScenario: ActivityScenario<HomeActivity>

    fun arrange(instance: HomeRobot.HomeRobotArrange.() -> Unit) =
        HomeRobot().HomeRobotArrange().apply(instance)

    inner class HomeRobotArrange {

        init {
            initKoin()
            setupViewModelMock()
        }

        infix fun act(func: HomeRobotAct.() -> Unit): HomeRobotAct = HomeRobotAct().apply(func)

        private fun initKoin() = loadKoinModules(module = module { viewModel { homeViewModel } })

        private fun setupViewModelMock() {
            every { homeViewModel.getHomeViewStateLiveData() } returns viewStateLiveData
        }

        fun showScreen() {
            activityScenario = ActivityScenario.launch(
                Intent(ApplicationProvider.getApplicationContext(), HomeActivity::class.java)
            )
        }

    }

    inner class HomeRobotAct: BaseRobotAction() {

        infix fun assert(func: HomeRobotAssert.() -> Unit) = HomeRobotAssert().apply(func)

        fun triggerLoadingOnViewState() = viewStateLiveData.postValue(HomeViewState.LoadingOn)

        fun triggerLoadingOffViewState() = viewStateLiveData.postValue(HomeViewState.LoadingOff)

        fun triggerSuccessfulListFetchingViewState() = viewStateLiveData.postValue(
            HomeViewState.SuccessfulListFetching(showListStub)
        )

        fun triggerConnectionErrorState() = viewStateLiveData.postValue(
            HomeViewState.FailedListFetching(ConnectionException())
        )

        fun triggerGenericErrorState() = viewStateLiveData.postValue(
            HomeViewState.FailedListFetching(Exception())
        )

        fun triggerSortedShowListState() = viewStateLiveData.postValue(
            HomeViewState.SortedList(sortedShowListStub)
        )

        fun sortByWorstVoted() {
            clickOnComponent(R.id.sortSpinner)
            clickOnText(R.string.worst_voted_label)
        }

        fun sortByBestVoted() {
            clickOnComponent(R.id.sortSpinner)
            clickOnText(R.string.best_voted_label)
        }

        fun sortByTitleAtoZ() {
            clickOnComponent(R.id.sortSpinner)
            clickOnText(R.string.a_to_z_label)
        }

        fun sortByTitleZtoA() {
            clickOnComponent(R.id.sortSpinner)
            clickOnText(R.string.z_to_a_label)
        }

        fun sortByAirDateNewestToOldest() {
            clickOnComponent(R.id.sortSpinner)
            clickOnText(R.string.air_date_newest_label)
        }

        fun sortByAirDateOldestToNewest() {
            clickOnComponent(R.id.sortSpinner)
            clickOnText(R.string.air_date_oldest_label)
        }

    }

    inner class HomeRobotAssert: BaseRobotAssertion() {

        infix fun finish(func: HomeRobotFinish.() -> Unit) = HomeRobotFinish().apply(func)

        fun checkVisibleLoading() = checkViewVisible(R.id.contentProgressBar)

        fun checkInvisibleLoading() = checkViewGone(R.id.contentProgressBar)

        fun checkVisibleSortingControls() {
            checkViewVisible(R.id.sortedByTextView)
            checkViewVisible(R.id.sortSpinner)
        }

        fun checkHiddenSortingControls() {
            checkViewGone(R.id.sortedByTextView)
            checkViewGone(R.id.sortSpinner)
        }

        fun checkVisibleShowList() = checkViewVisible(R.id.showsRecycleView)

        fun checkHiddenShowList() = checkViewGone(R.id.showsRecycleView)

        fun checkVisibleConnectionError() {
            checkViewVisible(R.id.containerError)
            checkTextExistence(R.id.errorTextView, R.string.connection_error_text)
        }

        fun checkVisibleGenericError() {
            checkViewVisible(R.id.containerError)
            checkTextExistence(R.id.errorTextView, R.string.general_error_text)
        }

        fun checkSortByWorstVotedChange() {
            verify { homeViewModel.sortShows(showListStub, SortOption.WorstVoted) }
        }

        fun checkSortByBestVotedChange() {
            verify { homeViewModel.sortShows(showListStub, SortOption.BestVoted) }
        }

        fun checkSortByTitleAtoZChange() {
            verify { homeViewModel.sortShows(showListStub, SortOption.TitleAZ) }
        }

        fun checkSortByTitleZtoAChange() {
            verify { homeViewModel.sortShows(showListStub, SortOption.TitleZA) }
        }

        fun checkSortByAirDateNewestToOldest() {
            verify { homeViewModel.sortShows(showListStub, SortOption.AirDateNewest) }
        }

        fun checkSortByAirDateOldestToNewest() {
            verify { homeViewModel.sortShows(showListStub, SortOption.AirDateOldest) }
        }

    }

    inner class HomeRobotFinish: BaseRobotFinish {

        override fun closeScreenAndReleaseResources() = activityScenario.close()

    }

    private fun createInstanceList(quantity: Int = 5): List<Show> {
        val shows = mutableListOf<Show>()

        for (index in 1..quantity) {
            shows.add(
                Show(
                    id = index,
                    name = "Stubbed Name $index",
                    posterPath = "https://poster.path.com/$index.jpg",
                    voteAverage = index.toFloat(),
                    airDate = "2020-01-$index",
                    formattedAirDate = "July 2019"
                )
            )
        }

        return shows
    }

}