package net.yuzumone.mikutter.fcm

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.concurrent.atomic.AtomicInteger
import android.app.NotificationChannel
import android.os.Build
import dagger.android.AndroidInjection
import net.yuzumone.mikutter.fcm.db.MikutterMessageDatabase
import net.yuzumone.mikutter.fcm.entity.MikutterMessage
import java.util.*
import javax.inject.Inject

class MikutterMessagingService : FirebaseMessagingService() {

    @Inject lateinit var database: MikutterMessageDatabase

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
    }

    override fun onMessageReceived(message: RemoteMessage?) {
        super.onMessageReceived(message)
        val data = message?.data ?: return
        val mikutterMessage = MikutterMessage(data, Date())
        createNotification(mikutterMessage)
        saveMessage(mikutterMessage)
    }

    private fun saveMessage(message: MikutterMessage) {
        val dao = database.messageDao()
        dao.insert(message)
    }

    private fun createNotification(message: MikutterMessage) {
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
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent
                .getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationBuilder = NotificationCompat.Builder(this, id)
                .setSmallIcon(android.R.drawable.ic_menu_send)
                .setContentTitle(message.title)
                .setContentText(message.body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)
        manager.notify(NotificationID.id, notificationBuilder.build())
    }

    class NotificationID {
        companion object {
            private val c = AtomicInteger(0)
            val id: Int get() = c.getAndIncrement()
        }
    }
}