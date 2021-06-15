package com.example.test.foreground

import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.example.test.R
import com.example.test.receiver.AudioReceiver


class ForegroundService : Service() {
    override fun onCreate() {
        super.onCreate()
    }
public var channel_id : String = "someid"
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
        val switchIntent = Intent(this, AudioReceiver::class.java)
        val pendingSwitchIntent = PendingIntent.getBroadcast(this, 100, switchIntent, 0)
        val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)
        notificationLayout.setTextViewText(R.id.notification_title, "Notification")
        notificationLayout.setOnClickPendingIntent(R.id.button_pause_song, pendingSwitchIntent);
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