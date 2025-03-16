package com.example.trashArchitecture

import android.app.Application
import com.example.bridge.KoinConfiguration

class TrashApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinConfiguration.setupKoinInjection(this)
    }
}
