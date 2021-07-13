package com.example.theshowhub.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theshowhub.utils.ConnectionException
import com.example.theshowhub.R
import com.example.theshowhub.api.Show
import com.example.theshowhub.databinding.ActivityHomeBinding
import com.example.theshowhub.extensions.makeItGone
import com.example.theshowhub.extensions.makeItVisible
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomeBinding
    private val viewModel: HomeViewModel by stateViewModel()
    private val showAdapter by lazy { ShowAdapter() }

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
        is HomeViewState.CachedShowsRestored -> onListFetched(homeViewState.shows)
    }

    private fun onSortedList(shows: List<Show>) {
        showAdapter.submitList(shows) {
            viewBinding.showsRecycleView.smoothScrollToPosition(0)
        }
    }

    private fun onLoadingStarted() = viewBinding.contentProgressBar.makeItVisible()

    private fun onLoadingFinished() = viewBinding.contentProgressBar.makeItGone()

    private fun onListFetched(shows: List<Show>) {
        viewBinding.containerError.makeItGone()
        viewBinding.showsRecycleView.adapter = showAdapter
        viewBinding.showsRecycleView.layoutManager = LinearLayoutManager(this)
        showAdapter.submitList(shows)
        setupSortSpinner()
        viewBinding.showsRecycleView.makeItVisible()
    }

    private fun onError(exception: Exception) {
        viewBinding.errorTextView.text = if (exception is ConnectionException)
            getString(R.string.connection_error_text)
        else
            getString(R.string.general_error_text)

        viewBinding.containerError.makeItVisible()
    }

    private fun createSelectionListener(): AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val sortOption = parent?.getItemAtPosition(position) as SortOption
                viewModel.sortShows(showAdapter.currentList, sortOption)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }

}