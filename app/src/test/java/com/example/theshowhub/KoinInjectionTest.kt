package com.example.theshowhub

import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class KoinInjectionTest: KoinTest {

    @Test
    fun `SHOULD module dependencies correctly`() {
        koinApplication {
            androidContext(mockk(relaxed = true))
            modules(appModule)
        }.checkModules()
    }

}