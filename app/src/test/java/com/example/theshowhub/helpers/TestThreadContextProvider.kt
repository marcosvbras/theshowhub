package com.example.theshowhub.helpers

import com.example.theshowhub.utils.ThreadContextProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestThreadContextProvider : ThreadContextProvider() {
    override val ui: CoroutineContext = Dispatchers.Unconfined
    override val cpu: CoroutineContext = Dispatchers.Unconfined
    override val io: CoroutineContext = Dispatchers.Unconfined
}