package appS3027403.dorapallysai.eventbooking.events

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import appS3027403.dorapallysai.eventbooking.R

class NotificationWorker(
    currentActivity: Context,
    workerParams: WorkerParameters
) : Worker(currentActivity, workerParams) {

    override fun doWork(): Result {
        // Create a notification
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Create a notification channel (for Android 8.0 and above)
        val channelId = "event_notification_channel"
        val notificationChannel = NotificationChannel(
            channelId,
            "Event Notifications",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(notificationChannel)

        // Create a notification
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setSmallIcon(R.drawable.ic_event_booking) // Replace with your icon
            .setContentTitle("Event Reminder")
            .setContentText("Don't forget about your upcoming event!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .build()

        // Show the notification
        notificationManager.notify(0, notification)

        return Result.success()
    }
}
