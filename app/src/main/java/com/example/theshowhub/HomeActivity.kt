package com.example.theshowhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theshowhub.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()
    private val showAdapter by lazy { ShowAdapter() }
    private var shows = listOf<Show>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupComponents()
        viewModel.fetchTopRatedShows()
    }

    private fun setupComponents() {
        viewModel.getHomeViewStateLiveData().observe(this) { it?.let { onViewStateUpdated(it) } }
    }

    private fun setupSortSpinner() {
        val options = SortOption.values().toList()
        val sortAdapter = SortAdapter(this, R.layout.item_option, options)

        viewBinding.sortSpinner.onItemSelectedListener = createSelectionListener()
        viewBinding.sortSpinner.adapter = sortAdapter

        viewBinding.sortedByTextView.makeItVisible()
        viewBinding.sortSpinner.makeItVisible()
    }

    private fun onViewStateUpdated(homeViewState: HomeViewState) = when(homeViewState) {
        is HomeViewState.LoadingOn -> onLoadingStarted()
        is HomeViewState.LoadingOff -> onLoadingFinished()
        is HomeViewState.SuccessfulListFetching -> onListFetched(homeViewState.shows)
        is HomeViewState.FailedListFetching -> onError(homeViewState.exception)
        is HomeViewState.SortedList -> onSortedList(homeViewState.shows)
    }

    private fun onSortedList(shows: List<Show>) {
        this.shows = shows
        showAdapter.setShows(shows)
    }

    private fun onLoadingStarted() = viewBinding.contentProgressBar.makeItVisible()

    private fun onLoadingFinished() = viewBinding.contentProgressBar.makeItGone()

    private fun onListFetched(shows: List<Show>) {
        this.shows = shows
        viewBinding.showsRecycleView.adapter = showAdapter
        viewBinding.showsRecycleView.layoutManager = LinearLayoutManager(this)
        showAdapter.setShows(shows)
        setupSortSpinner()
    }

    private fun onError(exception: Exception) {
        Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
    }

    private fun createSelectionListener(): AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val sortOption = parent?.getItemAtPosition(position) as SortOption
            viewModel.sortShows(shows, sortOption)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    }

}