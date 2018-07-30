package net.yuzumone.mikutter.fcm

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.support.v4.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.concurrent.atomic.AtomicInteger
import android.app.NotificationChannel
import android.os.Build

class MikutterMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        val data = message?.data ?: return
        val title = data["title"] ?: ""
        val body = data["body"] ?: ""
        val url = data["url"] ?: ""
        createNotification(title, body, url)
    }

    private fun createNotification(title: String, body: String, url :String) {
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val id = getString(R.string.channel_id)
        val name = getString(R.string.channel_name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(id, name, importance)
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            channel.enableVibration(true)
            channel.enableLights(true)
            manager.createNotificationChannel(channel)
        }
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, id)
                .setSmallIcon(android.R.drawable.ic_menu_send)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
        if (url.isNotBlank()) {
            notificationBuilder.addAction(createAction(url))
        }
        manager.notify(NotificationID.id, notificationBuilder.build())
    }

    private fun createAction(url: String): NotificationCompat.Action {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val stack = TaskStackBuilder.create(this)
        stack.addNextIntentWithParentStack(intent)
        val pendingIntent = stack.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        return NotificationCompat.Action.Builder(android.R.drawable.ic_menu_send, url, pendingIntent).build()
    }

    class NotificationID {
        companion object {
            private val c = AtomicInteger(0)
            val id: Int get() = c.getAndIncrement()
        }
    }
}