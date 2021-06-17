package com.example.test.service

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.test.MainActivity
import com.example.test.R
import com.example.test.receiver.AudioReceiver

class MusicService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val actionName = intent?.getStringExtra("myAction")
        intent?.action
        startInForeground(true)
        var index = 0
        var myArray = arrayOf<Int>(
            com.example.test.R.raw.song_1,
            com.example.test.R.raw.song_2,
            com.example.test.R.raw.song_3
        )
        var mp: MediaPlayer? = null
        if (intent?.action == "ACTION_PREVIOUS") {
            Toast.makeText(applicationContext, "previous", Toast.LENGTH_LONG).show()
            if (index != 0) {
                if (mp != null && mp.isPlaying()) {
                    mp.reset()
                    mp.stop()
                    mp.release()

                } else {

                }
                mp = MediaPlayer.create(applicationContext, myArray[index])
                mp.start()
                mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
                    if (!mp.isPlaying) {
                        mp.release()
                    } else {
                        mp.reset()
                        mp.stop()
                        mp.release()
                    }
                })
            }
        }
        if (intent?.action == "ACTION_NEXT") {
            Toast.makeText(applicationContext, "next", Toast.LENGTH_LONG).show()
            index += 1
            if (index != 0 && myArray.size >= index) {
                if (mp != null && mp.isPlaying()) {
                    mp.stop()
                    mp.reset()
                    mp.release()
                } else {

                }
                mp = MediaPlayer.create(applicationContext, myArray[index])
                mp.start()
                mp.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
                    if (!mp.isPlaying) {
                        mp.release()
                    } else {
                        mp.reset()
                        mp.stop()
                        mp.release()
                    }
                })
            }
        }
        if (intent?.action == "ACTION_PLAY") {
            if (mp != null && mp.isPlaying()) {
                mp.stop()
                mp.release()
            } else {

            }
            mp = MediaPlayer.create(applicationContext, myArray[index])
            mp.start()
        }
        mp?.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
            if (!mp.isPlaying) {
                mp.release()
            } else {
                mp.reset()
                mp.stop()
                mp.release()
            }
        })

        if (intent?.action == "ACTION_DUCK") {
            Toast.makeText(applicationContext, "DUCK", Toast.LENGTH_LONG).show()
            if (mp != null && mp.isPlaying()) {
                mp.stop()
                mp.release()

            } else {

            }
            mp = MediaPlayer.create(applicationContext, R.raw.duck_mania)
            mp.start()
            mp?.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp ->
                if (!mp.isPlaying) {
                    mp.release()
                } else {
                    mp.reset()
                    mp.stop()
                    mp.release()
                }
            })
        }
        return START_STICKY
    }
    @SuppressLint("RemoteViewLayout", "UseCompatLoadingForDrawables")
    private fun startInForeground(boolState : Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "CHANNEL_ID",
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
        // val notificationLayout = RemoteViews(packageName, R.layout.notification_layout)
        val notificationLayout = getCombinedRemoteViews(true)

        val prevIntent = Intent(this, AudioReceiver::class.java).setAction(ForegroundService.ACTION_PREVIOUS)
        val prevPendingIntent = PendingIntent.getBroadcast(applicationContext, 0, prevIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val nextIntent = Intent(this, AudioReceiver::class.java).setAction(ForegroundService.ACTION_NEXT)
        val nextPendingIntent = PendingIntent.getBroadcast(applicationContext, 0, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val playIntent = Intent(this, AudioReceiver::class.java).setAction(ForegroundService.ACTION_PLAY)
        val playPendingIntent = PendingIntent.getBroadcast(applicationContext , 0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val duckIntent = Intent(this, AudioReceiver::class.java).setAction(ForegroundService.ACTION_DUCK)
        val duckPendingIntent = PendingIntent.getBroadcast(applicationContext , 0, duckIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        notificationLayout.setTextViewText(R.id.notification_title, "Olur gibi")
        val drawableIcMediaPrevious= resources.getDrawable(android.R.drawable.ic_media_previous)
        notificationLayout.setImageViewBitmap(R.id.button_previous_song,drawableToBitmap(drawableIcMediaPrevious))
        val drawableIcMediaNext = resources.getDrawable(android.R.drawable.ic_media_next)
        notificationLayout.setImageViewBitmap(R.id.button_next_song,drawableToBitmap(drawableIcMediaNext))
        val drawableIcMediaPlay = resources.getDrawable(android.R.drawable.ic_media_pause)
        notificationLayout.setImageViewBitmap(R.id.button_pause_song,drawableToBitmap(drawableIcMediaPlay))
        if(ForegroundService.boolState){
            val drawableIcMediaPlay = resources.getDrawable(android.R.drawable.ic_media_play)
            notificationLayout.setImageViewBitmap(R.id.button_pause_song,drawableToBitmap(drawableIcMediaPlay))
        }
        notificationLayout.setOnClickPendingIntent(R.id.button_pause_song, playPendingIntent);
        notificationLayout.setOnClickPendingIntent(R.id.button_next_song, nextPendingIntent);
        notificationLayout.setOnClickPendingIntent(R.id.button_previous_song, prevPendingIntent);
        notificationLayout.setOnClickPendingIntent(R.id.imageDuckView,duckPendingIntent)
        val notification: Notification? = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setContentTitle(getString(R.string.app_name))
            .setContentText("test")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(notificationLayout)
            .build()
        startForeground(1, notification)
    }
    fun drawableToBitmap(drawable: Drawable): Bitmap? {
        var bitmap: Bitmap? = null
        if (drawable is BitmapDrawable) {
            val bitmapDrawable = drawable
            if (bitmapDrawable.bitmap != null) {
                return bitmapDrawable.bitmap
            }
        }
        bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight())
        drawable.draw(canvas)
        return bitmap
    }
    private fun getCombinedRemoteViews(collapsed: Boolean): RemoteViews {
        val remoteViews = RemoteViews(packageName, R.layout.notification_layout)
        return remoteViews
    }
}