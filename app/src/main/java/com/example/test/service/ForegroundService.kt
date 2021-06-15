package com.example.test.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.test.R
import com.example.test.receiver.AudioReceiver


class ForegroundService : Service() {
    override fun onCreate() {
        super.onCreate()
    }

    val CHANNEL_ID = "test"
    companion object {

    const val ACTION_NEXT = "ACTION_NEXT"
    const val ACTION_PREVIOUS = "ACTION_PREVIOUS"
    const val ACTION_PLAY = "ACTION_PLAY"

}
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startInForeground()
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @SuppressLint("RemoteViewLayout")
    private fun startInForeground() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "test",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)
        }
        val switchIntent = Intent(applicationContext, AudioReceiver::class.java)
        val bundle = Bundle()
        val pendingSwitchIntent = PendingIntent.getBroadcast(
            applicationContext,
            100,
            switchIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)


        val prevIntent = Intent(this, AudioReceiver::class.java).setAction(ACTION_PREVIOUS)
        val prevPendingIntent = PendingIntent.getBroadcast(applicationContext, 0, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val nextIntent = Intent(this, AudioReceiver::class.java).setAction(ACTION_NEXT)
        val nextPendingIntent = PendingIntent.getBroadcast(applicationContext, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val playIntent = Intent(this, AudioReceiver::class.java).setAction(ACTION_PLAY)
        val playPendingIntent = PendingIntent.getBroadcast(applicationContext , 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        notificationLayout.setTextViewText(R.id.notification_title, "Notification")

        notificationLayout.setOnClickPendingIntent(R.id.button_pause_song, playPendingIntent);

        notificationLayout.setOnClickPendingIntent(R.id.button_next_song, nextPendingIntent);

        notificationLayout.setOnClickPendingIntent(R.id.button_previous_song, prevPendingIntent);


        val notification: Notification? = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
            .setContentText("test")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .build()
        startForeground(1, notification)
    }
}