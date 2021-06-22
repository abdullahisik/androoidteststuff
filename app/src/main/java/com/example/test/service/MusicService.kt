package com.example.test.service

import android.app.*
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import androidx.annotation.Nullable
import com.example.test.R

class MusicService : Service() {
    //creating a mediaplayer object
    private var player: MediaPlayer? = null

    @Nullable
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    var sonarray = arrayOf<Int>(
          com.example.test.R.raw.song_1,
          com.example.test.R.raw.song_2,
          com.example.test.R.raw.song_3
    )
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player = MediaPlayer.create(this, R.raw.duck_mania)
        player!!.isLooping = false
        player!!.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        player!!.stop()
        player = null

    }
}