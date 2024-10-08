package com.example.media

import android.content.Context
import android.content.Intent
import android.util.Log

class MediaServiceConnection(
    private val context: Context,
) {
    private var isServiceRunning = false
    fun destroyService() {
        context.stopService(Intent(context, MediaService::class.java))
        isServiceRunning = false
        Log.d("NinhTBM", "destroy service")
    }

    fun startService() {
        if (!isServiceRunning) {
            Log.d("NinhTBM", "start service")
            val intent = Intent(context, MediaService::class.java)
            context.startForegroundService(intent)
            isServiceRunning = true
        }
    }
}