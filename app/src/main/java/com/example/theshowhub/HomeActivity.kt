package com.example.theshowhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        viewModel.getHomeViewStateLiveData()
            .observe(this, { it?.let { onViewStateUpdated(it) } })
    }

    private fun onViewStateUpdated(homeViewState: HomeViewState) = when(homeViewState) {
        is HomeViewState.LoadingOn -> onLoadingStarted()
        is HomeViewState.LoadingOff -> onLoadingFinished()
        is HomeViewState.SuccessfulListFetching -> onListFetched(homeViewState.movies)
        is HomeViewState.FailedListFetching -> onError(homeViewState.exception)
    }

    private fun onLoadingStarted() {

    }

    private fun onLoadingFinished() {

    }

    private fun onListFetched(movies: List<Movie>) {

    }

    private fun onError(exception: Exception) {

    }

}