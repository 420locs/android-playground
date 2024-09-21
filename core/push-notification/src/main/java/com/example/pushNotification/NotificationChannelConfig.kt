package com.example.pushNotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

enum class NotificationChannelConfig(
    private val channelName: String,
    private val channelId: String,
    private val description: String,
    private val priority: Int,
    private val vibrationPattern: LongArray,
) {
    Default(
        "Default",
        "default",
        "Default",
        NotificationManager.IMPORTANCE_DEFAULT,
        longArrayOf(100, 250, 100, 500)
    );

    /**
     * Creates a notification for configured for news updates
     */
    internal fun createNewsNotification(
        context: Context,
        block: NotificationCompat.Builder.() -> Unit,
    ): Notification {
        context.ensureNotificationChannelExists(this)
        return NotificationCompat.Builder(
            context,
            this.channelId,
        ).setPriority(this.priority)
            .setVibrate(this.vibrationPattern)
            .apply(block)
            .build()
    }

    /**
     * Ensures that a notification channel is present if applicable
     */
    private fun Context.ensureNotificationChannelExists(channelConfig: NotificationChannelConfig) {
        val channel = NotificationChannel(
            channelConfig.channelId,
            channelConfig.channelName,
            channelConfig.priority,
        ).apply {
            description = channelConfig.description
        }
        // Register the channel with the system
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }
}