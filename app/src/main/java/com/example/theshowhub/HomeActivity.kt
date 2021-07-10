package com.example.theshowhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theshowhub.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

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

    private fun onViewStateUpdated(homeViewState: HomeViewState) = when(homeViewState) {
        is HomeViewState.LoadingOn -> onLoadingStarted()
        is HomeViewState.LoadingOff -> onLoadingFinished()
        is HomeViewState.SuccessfulListFetching -> onListFetched(homeViewState.shows)
        is HomeViewState.FailedListFetching -> onError(homeViewState.exception)
    }

    private fun onLoadingStarted() = viewBinding.contentProgressBar.makeItVisible()

    private fun onLoadingFinished() = viewBinding.contentProgressBar.makeItGone()

    private fun onListFetched(shows: List<Show>) {
        val adapter = ShowAdapter()
        viewBinding.showsRecycleView.adapter = adapter
        viewBinding.showsRecycleView.layoutManager = LinearLayoutManager(this)
        adapter.setShows(shows)
    }

    private fun onError(exception: Exception) {
        Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
    }

}