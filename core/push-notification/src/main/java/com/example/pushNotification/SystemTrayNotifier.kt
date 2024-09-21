package com.example.pushNotification

import android.Manifest
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

/**
 * Implementation of [Notifier] that displays notifications in the system tray.
 *
 * Currently koin-annotation not implemented in project yet, so [SystemTrayNotifier] will be a singleton object for later migration.
 */

object SystemTrayNotifier {
    private const val MAX_NUM_NOTIFICATIONS = 5

    fun postNotifications(
        context: Context,
        newsResources: List<ItemNotification>,
        channelConfig: NotificationChannelConfig = NotificationChannelConfig.Default,
        summaryTitle: String = "All"
    ) = context.postNewsNotifications(newsResources, summaryTitle, channelConfig)

    private fun Context.postNewsNotifications(
        newsResources: List<ItemNotification>,
        summaryTitle: String,
        channelConfig: NotificationChannelConfig
    ) {
        // POST_NOTIFICATIONS required from Android 13
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val truncatedNewsResources = newsResources.take(MAX_NUM_NOTIFICATIONS)

        val newsNotifications = truncatedNewsResources.map { newsResource ->
            channelConfig.createNewsNotification(this) {
                setSmallIcon(android.R.drawable.btn_star)
                    .setContentTitle(newsResource.title)
                    .setContentText(newsResource.body)
                    .setContentIntent(createPendingIntent())
                    .setGroup("Test Notification")
                    .setAutoCancel(true)
            }
        }
        val summaryNotification = channelConfig.createNewsNotification(this) {
            setSmallIcon(android.R.drawable.btn_star)
                .setContentTitle(summaryTitle)
                .setContentText(summaryTitle)
                // Build summary info into InboxStyle template.
                .setStyle(getNotificationStyle(truncatedNewsResources, summaryTitle))
                .setGroup("Test Notification")
                .setGroupSummary(true)
                .setAutoCancel(true)
                .build()
        }

        // Send the notifications
        val notificationManager = NotificationManagerCompat.from(this)
        newsNotifications.forEachIndexed { index, notification ->
            notificationManager.notify(
                truncatedNewsResources[index].hashCode(),
                notification,
            )
        }
        notificationManager.notify(1, summaryNotification)
    }

    /**
     * Creates an inbox style summary notification for news updates
     */
    private fun getNotificationStyle(
        notificationItems: List<ItemNotification>,
        title: String,
    ): NotificationCompat.InboxStyle = notificationItems
        .fold(NotificationCompat.InboxStyle()) { inboxStyle, item ->
            inboxStyle.addLine(
                item.title,
            )
        }
        .setBigContentTitle(title)
        .setSummaryText(title)

    private fun Context.createPendingIntent(): PendingIntent? = PendingIntent.getActivity(
        this,
        0,
        Intent().apply {
            action = Intent.ACTION_VIEW
            component = ComponentName(
                packageName,
                "com.example.trashArchitecture.MainActivity",
            )
        },
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )
}



