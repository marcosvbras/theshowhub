package com.example.theshowhub.home

import androidx.lifecycle.SavedStateHandle
import com.example.theshowhub.di.appModule
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.dsl.module

class KoinInjectionTest {

    @Test
    fun `SHOULD module dependencies correctly`() {
        koinApplication {
            androidContext(mockk(relaxed = true))
            modules(module { factory { mockk<SavedStateHandle>() } } + appModule)
        }
    }

}