package com.maverick.unittestingexample_cc.utils

import kotlinx.coroutines.*

class Utils(val dispatchers: CoroutineDispatcher) {

    // Without Dispatcher
    suspend fun getUserName(): String {
        delay(10000)
        return "CheezyCode"
    }

    // With Dispatcher
    suspend fun getUser(): String {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
        }
        return "User - CheezyCode"
    }

    suspend fun getAddress(): String {
        withContext(dispatchers) {
            delay(5000)
        }
        return "Address"
    }

    var globalArg = false
    fun getAddressDetail() {
        CoroutineScope(dispatchers).launch {
            globalArg = true
        }
    }

}