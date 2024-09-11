package com.example.trashArchitecture

import android.app.Application
import com.example.bridge.dataModule
import com.example.bridge.sampleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(
                dataModule,
                sampleModule,
                // todo: add all modules here
            )
        }
    }
}
