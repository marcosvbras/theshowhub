package com.example.theshowhub

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TheShowHubApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TheShowHubApplication)
            modules(appModule)
        }
    }

}