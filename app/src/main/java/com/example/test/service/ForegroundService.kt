package com.example.test.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.test.R
import com.example.test.receiver.AudioReceiver


class ForegroundService : Service() {
    override fun onCreate() {
        super.onCreate()
    }
    val CHANNEL_ID = "test"

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
        bundle.putString("state","bundle work")
        switchIntent.putExtra("test","test")
        val pendingSwitchIntent = PendingIntent.getBroadcast(applicationContext, 100, switchIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)
        notificationLayout.setTextViewText(R.id.notification_title, "")

        notificationLayout.setOnClickPendingIntent(R.id.button_pause_song, pendingSwitchIntent);
        notificationLayout.setOnClickPendingIntent(R.id.button_next_song, pendingSwitchIntent);
        notificationLayout.setOnClickPendingIntent(R.id.button_previous_song, pendingSwitchIntent);
        Log.d("TAG", "my Message")

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