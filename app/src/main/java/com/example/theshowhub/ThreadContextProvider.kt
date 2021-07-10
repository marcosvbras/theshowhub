package com.example.theshowhub

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class ThreadContextProvider {
    open val ui: CoroutineContext by lazy { Dispatchers.Main }
    open val cpu: CoroutineContext by lazy { Dispatchers.Default }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
}